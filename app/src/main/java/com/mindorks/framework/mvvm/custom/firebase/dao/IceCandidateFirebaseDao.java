package com.mindorks.framework.mvvm.custom.firebase.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.model.IceCandidateFirebaseData;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public interface IceCandidateFirebaseDao {

    @NonNull Observable<IceCandidateFirebaseData> getIceCandidate(@NonNull Query query);

    @NonNull Observable<IceCandidateFirebaseData> addIceCandidateListener(@NonNull Query query);

    void insertIceCandidate(@NonNull DatabaseReference databaseReference,
                            @NonNull IceCandidateFirebaseData iceCandidateFirebaseData);
}
