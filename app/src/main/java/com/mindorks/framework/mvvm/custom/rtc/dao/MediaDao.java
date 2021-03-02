package com.mindorks.framework.mvvm.custom.rtc.dao;

import com.mindorks.framework.mvvm.custom.common.CameraType;

import org.webrtc.AudioTrack;
import org.webrtc.VideoTrack;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface MediaDao {

    Single<Boolean> isCameraTypeAvailable(CameraType cameraType);

    Single<Boolean> startCapturingVideoAudio(CameraType cameraType);

    Observable<Boolean> startCapturingVideo(CameraType cameraType);

    Observable<Boolean> startCapturingAudio();

    Single<VideoTrack> getLocalVideoTrack();

    Single<AudioTrack> getLocalAudioTrack();

    Observable<VideoTrack> onRemoteStream();
}
