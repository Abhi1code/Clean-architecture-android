package com.mindorks.framework.mvvm.custom.remote.exception;

import androidx.annotation.NonNull;

public class RemoteDataNullException extends Exception {

    public RemoteDataNullException() {

    }

    public RemoteDataNullException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public RemoteDataNullException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RemoteDataNullException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
