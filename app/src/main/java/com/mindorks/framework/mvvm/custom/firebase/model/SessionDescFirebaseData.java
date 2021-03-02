package com.mindorks.framework.mvvm.custom.firebase.model;

import androidx.annotation.NonNull;

public class SessionDescFirebaseData {

    private final String sdp;

    private final String type;

    public SessionDescFirebaseData(@NonNull String sdp, @NonNull String type) {
        this.sdp = sdp;
        this.type = type;
    }

    public String getSdp() {
        return sdp;
    }

    public String getType() {
        return type;
    }
}
