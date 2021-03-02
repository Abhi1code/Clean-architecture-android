package com.mindorks.framework.mvvm.custom.rtc.source;

import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbackProvider;
import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbacks;
import com.mindorks.framework.mvvm.custom.rtc.dao.DataChannelDao;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.BaseRtcClient;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class DataChannelDaoImpl implements DataChannelDao {

    @NonNull private final BaseRtcClient baseRtcClient;
    @NonNull private final RtcCallbackProvider rtcCallbackProvider;

    public DataChannelDaoImpl(@NonNull BaseRtcClient baseRtcClient,
                             @NonNull RtcCallbackProvider rtcCallbackProvider) {
        this.baseRtcClient = baseRtcClient;
        this.rtcCallbackProvider = rtcCallbackProvider;
    }

    // Must observe for changes
    @Override
    public Observable<byte[]> onMessage() {
        return Observable.create(emitter -> {
            RtcCallbacks.MessageCallback messageCallback = msg -> {
                if (!emitter.isDisposed()) emitter.onNext(msg);
            };
            rtcCallbackProvider.addMessageCallback(messageCallback);
            emitter.setCancellable(rtcCallbackProvider::removeMessageCallback);
        });
    }

    @Override
    public Observable<Boolean> onDataChannelStateChange() {
        return Observable.create(emitter -> {
            RtcCallbacks.DataChannelStateChangeCallback dataChannelStateChangeCallback = state -> {
                if (!emitter.isDisposed()) emitter.onNext(state);
            };
            rtcCallbackProvider.addDataChannelOpenStateChangeCallback(dataChannelStateChangeCallback);
            emitter.setCancellable(rtcCallbackProvider::removeDataChannelOpenStateChangeCallback);
        });
    }
}
