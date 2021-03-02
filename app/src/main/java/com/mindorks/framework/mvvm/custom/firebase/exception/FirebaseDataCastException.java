package com.mindorks.framework.mvvm.custom.firebase.exception;

import androidx.annotation.NonNull;

public class FirebaseDataCastException extends Exception {

    public FirebaseDataCastException() {

    }

    public FirebaseDataCastException(@NonNull String detailMessage) {
        super(detailMessage);
    }

    public FirebaseDataCastException(@NonNull String detailMessage, @NonNull Throwable throwable) {
        super(detailMessage, throwable);
    }

    public FirebaseDataCastException(@NonNull Throwable throwable) {
        super(throwable);
    }
}
