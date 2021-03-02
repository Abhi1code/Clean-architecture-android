package com.mindorks.framework.mvvm.custom.rtc.source.callback;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;

import org.webrtc.VideoTrack;

import androidx.annotation.NonNull;

public interface RtcCallbacks {

    interface IceCandidateCallback {
        void onIceCandidate(@NonNull BaseIceCandidateData baseIceCandidateData);
    }

    interface SessionDescCallback {
        void onSessionDescription(@NonNull BaseSessionDescData baseSessionDescData);
    }

    interface RemoteStreamCallback {
        void onRemoteStreamAdded(@NonNull VideoTrack videoTrack);
    }

    interface ConnectionStateChangeCallback {
        void onConnectionOpenStateChange(boolean state);
    }

    interface MessageCallback {
        void onMessage(@NonNull byte[] msg);
    }

    interface DataChannelStateChangeCallback {
        void onDataChannelOpenStateChange(boolean state);
    }

    interface ErrorCallback {
        void onError(@NonNull Exception e);
    }
}
