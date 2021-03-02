package com.mindorks.framework.mvvm.custom.remote.exception;

import com.google.firebase.database.DatabaseError;

import androidx.annotation.NonNull;

public class RemoteCallException extends Exception {

    private String error;
    private int statusCode;

    public RemoteCallException(int statusCode, @NonNull String errorMessage) {
        this.error = errorMessage;
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public int getErrorCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "RemoteCallException{" +
                "error=" + error +
                '}';
    }
}
