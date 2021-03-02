package com.mindorks.framework.mvvm.custom.rtc.source;

import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbackProvider;
import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbacks;
import com.mindorks.framework.mvvm.custom.rtc.dao.ConnectionDao;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.BaseRtcClient;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;

public class ConnectionDaoImpl implements ConnectionDao {

    @NonNull private final BaseRtcClient baseRtcClient;
    @NonNull private final RtcCallbackProvider rtcCallbackProvider;

    public ConnectionDaoImpl(@NonNull BaseRtcClient baseRtcClient,
                             @NonNull RtcCallbackProvider rtcCallbackProvider) {
        this.baseRtcClient = baseRtcClient;
        this.rtcCallbackProvider = rtcCallbackProvider;
    }

    // One time call
    @Override
    public Single<Boolean> initiateConnectionAsInitiator() {
        return Single.create(emitter -> {
            baseRtcClient.createPeerConnection();
            baseRtcClient.createDataChannel();
            baseRtcClient.addStreamToConnection();
            baseRtcClient.createOffer();
            if (!emitter.isDisposed()) emitter.onSuccess(true);
        });
    }

    @Override
    public Single<Boolean> initiateConnectionAsAcceptor() {
        return Single.create(emitter -> {
            baseRtcClient.createPeerConnection();
            baseRtcClient.createDataChannel();
            baseRtcClient.addStreamToConnection();
            baseRtcClient.createAnswer();
            if (!emitter.isDisposed()) emitter.onSuccess(true);
        });
    }

    @Override
    public Single<Boolean> closeConnection() {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<Boolean> emitter) throws Exception {
                baseRtcClient.closeConnection();
                if (!emitter.isDisposed()) emitter.onSuccess(true);
            }
        });
    }

    // Must listen for changes
    @Override
    public Observable<BaseIceCandidateData> addIceCandidateListener() {
        return Observable.create(emitter -> {
            RtcCallbacks.IceCandidateCallback iceCandidateCallback = baseIceCandidateData -> {
                if (!emitter.isDisposed()) emitter.onNext(baseIceCandidateData);
            };
            rtcCallbackProvider.addIceCandidateCallback(iceCandidateCallback);
            emitter.setCancellable(rtcCallbackProvider::removeIceCandidateCallback);
        });
    }

    @Override
    public Observable<BaseSessionDescData> addSessionDescListener() {
        return Observable.create(emitter -> {
            RtcCallbacks.SessionDescCallback sessionDescCallback = baseSessionDescData -> {
                if (!emitter.isDisposed()) emitter.onNext(baseSessionDescData);
            };
            rtcCallbackProvider.addSessionDescriptionCallback(sessionDescCallback);
            emitter.setCancellable(rtcCallbackProvider::removeSessionDescriptionCallback);
        });
    }

    @Override
    public Observable<Boolean> addConnectionStateChangeListener() {
        return Observable.create(emitter -> {
            RtcCallbacks.ConnectionStateChangeCallback connectionStateChangeCallback = state -> {
                if (!emitter.isDisposed()) emitter.onNext(state);
            };
            rtcCallbackProvider.addConnectionOpenStateChangeCallback(connectionStateChangeCallback);
            emitter.setCancellable(rtcCallbackProvider::removeConnectionOpenStateChangeCallback);
        });
    }

    @Override
    public Observable<Exception> addErrorListener() {
        return Observable.create(emitter -> {
            RtcCallbacks.ErrorCallback errorCallback = e -> {
                if (!emitter.isDisposed()) emitter.onNext(e);
            };
            rtcCallbackProvider.addErrorCallback(errorCallback);
            emitter.setCancellable(rtcCallbackProvider::removeErrorCallback);
        });
    }
}
