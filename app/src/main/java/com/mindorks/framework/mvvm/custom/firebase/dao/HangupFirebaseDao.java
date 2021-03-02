package com.mindorks.framework.mvvm.custom.firebase.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.model.HangupFirebaseData;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public interface HangupFirebaseDao {

    @NonNull
    Observable<HangupFirebaseData> getHangupFlag(@NonNull Query query);

    @NonNull Observable<HangupFirebaseData> addHangupListener(@NonNull Query query);

    void updateHangupFlag(@NonNull DatabaseReference databaseReference,
                            @NonNull HangupFirebaseData hangupFirebaseData);
}
