package com.mindorks.framework.mvvm.custom.rtc.webrtc.observers;

import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

import timber.log.Timber;

public class CustomSdpObserver implements SdpObserver {

    @Override
    public void onCreateSuccess(SessionDescription sessionDescription) {
        Timber.v("onCreateSuccess ", sessionDescription.toString());
    }

    @Override
    public void onSetSuccess() {
        Timber.v("onSetSuccess ");
    }

    @Override
    public void onCreateFailure(String s) {
        Timber.v("onCreateFailure ", s);
    }

    @Override
    public void onSetFailure(String s) {
        Timber.v("onSetFailure ", s);
    }
}
