package com.mindorks.framework.mvvm.custom.remote.exception;

import androidx.annotation.NonNull;

public class RemoteDataCastException extends Exception {

    public RemoteDataCastException() {}

    public RemoteDataCastException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public RemoteDataCastException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RemoteDataCastException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
