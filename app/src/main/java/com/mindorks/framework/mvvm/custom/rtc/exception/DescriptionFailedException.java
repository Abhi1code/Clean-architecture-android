package com.mindorks.framework.mvvm.custom.rtc.exception;

import androidx.annotation.NonNull;

public class DescriptionFailedException extends Exception {

    public DescriptionFailedException() { }

    public DescriptionFailedException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public DescriptionFailedException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DescriptionFailedException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
