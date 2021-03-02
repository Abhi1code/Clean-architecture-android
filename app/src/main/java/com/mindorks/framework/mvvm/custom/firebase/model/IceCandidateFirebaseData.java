package com.mindorks.framework.mvvm.custom.firebase.model;


import androidx.annotation.NonNull;

public class IceCandidateFirebaseData {

    private final String sdp;
    private final String sdpMid;
    private final int sdpMLineIndex;

    public IceCandidateFirebaseData(@NonNull String sdp, @NonNull String sdpMid, int sdpMLineIndex) {
        this.sdp = sdp;
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getSdp() {
        return sdp;
    }

    public String getSdpMid() {
        return sdpMid;
    }

    public int getSdpMLineIndex() {
        return sdpMLineIndex;
    }
}
