package com.mindorks.framework.mvvm.custom.rtc.webrtc.observers;

import org.webrtc.CameraVideoCapturer;

import timber.log.Timber;

public class CustomCameraEventsHandler implements CameraVideoCapturer.CameraEventsHandler {

    public CustomCameraEventsHandler() { }

    @Override
    public void onCameraError(String s) {
        Timber.v("onCameraError ", s);
    }

    @Override
    public void onCameraDisconnected() {
        Timber.v("onCameraDisconnected ");
    }

    @Override
    public void onCameraFreezed(String s) {
        Timber.v("onCameraFreezed ", s);
    }

    @Override
    public void onCameraOpening(String s) {
        Timber.v("onCameraOpening ", s);
    }

    @Override
    public void onFirstFrameAvailable() {
        Timber.v("onFirstFrameAvailable ");
    }

    @Override
    public void onCameraClosed() {
        Timber.v("onCameraClosed ");
    }
}
