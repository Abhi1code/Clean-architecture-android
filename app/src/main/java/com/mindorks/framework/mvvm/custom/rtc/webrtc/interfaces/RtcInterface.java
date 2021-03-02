package com.mindorks.framework.mvvm.custom.rtc.webrtc.interfaces;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;

import org.webrtc.VideoTrack;

import androidx.annotation.NonNull;

public interface RtcInterface {

    void onIceCandidate(@NonNull BaseIceCandidateData baseIceCandidateData);

    void onRemoteStreamAdded(@NonNull VideoTrack videoTrack);

    void onSessionDescription(@NonNull BaseSessionDescData baseSessionDescData);

    void onConnectionOpenStateChange(boolean state);

    void onMessage(@NonNull byte[] msg);

    void onDataChannelOpenStateChange(boolean state);

    void onError(@NonNull Exception e);
}
