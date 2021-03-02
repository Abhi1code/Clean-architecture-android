package com.mindorks.framework.mvvm.custom.firebase.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.model.SessionDescFirebaseData;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public interface SdpFirebaseDao {

    @NonNull
    Observable<SessionDescFirebaseData> getSdp(@NonNull Query query);

    @NonNull Observable<SessionDescFirebaseData> addSdpListener(@NonNull Query query);

    void insertSdp(@NonNull DatabaseReference databaseReference,
                            @NonNull SessionDescFirebaseData sessionDescFirebaseData);
}
