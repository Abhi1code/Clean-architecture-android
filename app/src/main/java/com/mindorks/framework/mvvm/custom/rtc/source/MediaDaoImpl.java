package com.mindorks.framework.mvvm.custom.rtc.source;

import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbackProvider;
import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbacks;
import com.mindorks.framework.mvvm.custom.rtc.dao.MediaDao;
import com.mindorks.framework.mvvm.custom.common.CameraType;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.BaseRtcClient;

import org.webrtc.AudioTrack;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoTrack;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MediaDaoImpl implements MediaDao {

    @NonNull private final BaseRtcClient baseRtcClient;
    @NonNull private final RtcCallbackProvider rtcCallbackProvider;

    public MediaDaoImpl(@NonNull BaseRtcClient baseRtcClient,
                        @NonNull RtcCallbackProvider rtcCallbackProvider) {
        this.baseRtcClient = baseRtcClient;
        this.rtcCallbackProvider = rtcCallbackProvider;
    }

    // One time call
    @Override
    public Single<Boolean> isCameraTypeAvailable(CameraType cameraType) {
        return Single.create(emitter -> {
            if (!emitter.isDisposed())
                emitter.onSuccess(baseRtcClient.isCameraTypeAvailable(cameraType));
        });
    }

    @Override
    public Single<Boolean> startCapturingVideoAudio(CameraType cameraType) {
        return Single.create(emitter -> {
            VideoCapturer videoCapturer = baseRtcClient.createCameraCapturer(cameraType);
            if (videoCapturer == null && !emitter.isDisposed()) {
                emitter.onSuccess(false);
            } else {
                baseRtcClient.startVideoCapturing(videoCapturer);
                baseRtcClient.startAudioCapturing();
                if (!emitter.isDisposed()) emitter.onSuccess(true);
            }
        });
    }

    @Override
    public Observable<Boolean> startCapturingVideo(CameraType cameraType) {
        return Observable.create(emitter -> {
            VideoCapturer videoCapturer = baseRtcClient.createCameraCapturer(cameraType);
            if (videoCapturer == null && !emitter.isDisposed()) {
                emitter.onNext(false);
            } else {
                baseRtcClient.startVideoCapturing(videoCapturer);
                if (!emitter.isDisposed()) emitter.onNext(true);
            }
        });
    }

    @Override
    public Observable<Boolean> startCapturingAudio() {
        return Observable.create(emitter -> {
            baseRtcClient.startAudioCapturing();
            if (!emitter.isDisposed()) emitter.onNext(true);
        });
    }

    @Override
    public Single<VideoTrack> getLocalVideoTrack() {
        return Single.create(emitter -> {
            if (!emitter.isDisposed()) emitter.onSuccess(baseRtcClient.getVideoTrack());
        });
    }

    @Override
    public Single<AudioTrack> getLocalAudioTrack() {
        return Single.create(emitter -> {
            if (!emitter.isDisposed()) emitter.onSuccess(baseRtcClient.getAudioTrack());
        });
    }

    // Must observe for changes
    @Override
    public Observable<VideoTrack> onRemoteStream() {
        return Observable.create(emitter -> {
            RtcCallbacks.RemoteStreamCallback remoteStreamCallback = videoTrack -> {
                if (!emitter.isDisposed()) emitter.onNext(videoTrack);
            };
            rtcCallbackProvider.addRemoteStreamCallback(remoteStreamCallback);
            emitter.setCancellable(rtcCallbackProvider::removeRemoteStreamCallback);
        });
    }
}
