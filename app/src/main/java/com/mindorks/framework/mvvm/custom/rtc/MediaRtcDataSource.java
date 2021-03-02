package com.mindorks.framework.mvvm.custom.rtc;

import com.mindorks.framework.mvvm.custom.rtc.dao.MediaDao;
import com.mindorks.framework.mvvm.custom.common.CameraType;

import org.webrtc.AudioTrack;
import org.webrtc.VideoTrack;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MediaRtcDataSource {

    @NonNull private final MediaDao mMediaDao;

    public MediaRtcDataSource(@NonNull MediaDao mediaDao) {
        if (mediaDao == null)
            throw new IllegalArgumentException("Media Dao should not be null");
        mMediaDao = mediaDao;
    }

    public Single<Boolean> isCameraTypeAvailable(CameraType cameraType) {
        return mMediaDao.isCameraTypeAvailable(cameraType);
    }

    public Single<Boolean> startCapturingVideoAudio(CameraType cameraType) {
        return mMediaDao.startCapturingVideoAudio(cameraType);
    }

    public Observable<Boolean> startCapturingVideo(CameraType cameraType) {
        return mMediaDao.startCapturingVideo(cameraType);
    }

    public Observable<Boolean> startCapturingAudio() {
        return mMediaDao.startCapturingAudio();
    }

    public Single<VideoTrack> getLocalVideoTrack() {
        return mMediaDao.getLocalVideoTrack();
    }

    public Single<AudioTrack> getLocalAudioTrack() {
        return mMediaDao.getLocalAudioTrack();
    }

    public Observable<VideoTrack> onRemoteStream() {
        return mMediaDao.onRemoteStream();
    }
}
