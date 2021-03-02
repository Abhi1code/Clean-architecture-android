package com.mindorks.framework.mvvm.custom.firebase.exception;

import com.google.firebase.database.DatabaseError;

import androidx.annotation.NonNull;

public class FirebaseDataException extends Exception {

    protected DatabaseError error;

    public FirebaseDataException(@NonNull DatabaseError error) {
        this.error = error;
    }

    public DatabaseError getError() {
        return error;
    }

    @Override
    public String toString() {
        return "FirebaseDataException{" +
                "error=" + error +
                '}';
    }
}
