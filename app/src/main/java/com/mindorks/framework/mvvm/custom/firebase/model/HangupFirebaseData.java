package com.mindorks.framework.mvvm.custom.firebase.model;

public class HangupFirebaseData {

    private final boolean hangup;

    public HangupFirebaseData(boolean hangup) {
        this.hangup = hangup;
    }

    public boolean isHangup() {
        return hangup;
    }
}
