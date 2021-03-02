package com.mindorks.framework.mvvm.custom.repository;

import com.mindorks.framework.mvvm.custom.common.CameraType;
import com.mindorks.framework.mvvm.custom.rtc.MediaRtcDataSource;

import org.webrtc.AudioTrack;
import org.webrtc.VideoTrack;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MediaRepository {

    @NonNull private final MediaRtcDataSource mMediaRtcDataSource;

    public MediaRepository(@NonNull MediaRtcDataSource mediaRtcDataSource) {
        mMediaRtcDataSource = mediaRtcDataSource;
    }

    public Single<Boolean> isCameraTypeAvailable(@NonNull CameraType cameraType) {
        return mMediaRtcDataSource.isCameraTypeAvailable(cameraType);
    }

    public Single<Boolean> startCapturing(@NonNull CameraType cameraType) {
        return mMediaRtcDataSource.startCapturingVideoAudio(cameraType);
    }

    public Observable<Boolean> startCapturingVideo(@NonNull CameraType cameraType) {
        return mMediaRtcDataSource.startCapturingVideo(cameraType);
    }

    public Observable<Boolean> startCapturingAudio() {
        return mMediaRtcDataSource.startCapturingAudio();
    }

    public Single<VideoTrack> getLocalVideoTrack() {
        return mMediaRtcDataSource.getLocalVideoTrack();
    }

    public Single<AudioTrack> getLocalAudioTrack() {
        return mMediaRtcDataSource.getLocalAudioTrack();
    }

    public Observable<VideoTrack> onRemoteStream() {
        return mMediaRtcDataSource.onRemoteStream();
    }
}
