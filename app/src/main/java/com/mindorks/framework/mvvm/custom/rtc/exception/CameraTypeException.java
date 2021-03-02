package com.mindorks.framework.mvvm.custom.rtc.exception;

import androidx.annotation.NonNull;

public class CameraTypeException extends Exception {

    public CameraTypeException() { }

    public CameraTypeException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public CameraTypeException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CameraTypeException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
