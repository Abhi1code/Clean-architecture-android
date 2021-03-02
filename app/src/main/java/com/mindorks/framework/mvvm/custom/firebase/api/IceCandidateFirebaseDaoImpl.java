package com.mindorks.framework.mvvm.custom.firebase.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.dao.IceCandidateFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.model.IceCandidateFirebaseData;
import com.mindorks.framework.mvvm.custom.firebase.rx.BaseRxFirebase;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class IceCandidateFirebaseDaoImpl implements IceCandidateFirebaseDao {

    @NonNull private final BaseRxFirebase baseRxFirebase;

    public IceCandidateFirebaseDaoImpl(@NonNull BaseRxFirebase baseRxFirebase) {
        this.baseRxFirebase = baseRxFirebase;
    }

    @NonNull
    @Override
    public Observable<IceCandidateFirebaseData> getIceCandidate(@NonNull Query query) {
        return baseRxFirebase.getObservableForSingleEvent(query, IceCandidateFirebaseData.class);
    }

    @NonNull
    @Override
    public Observable<IceCandidateFirebaseData> addIceCandidateListener(@NonNull Query query) {
        return baseRxFirebase.getObservable(query, IceCandidateFirebaseData.class);
    }

    @Override
    public void insertIceCandidate(@NonNull DatabaseReference databaseReference,
                                   @NonNull IceCandidateFirebaseData iceCandidateFirebaseData) {
        baseRxFirebase.insertData(databaseReference, iceCandidateFirebaseData);
    }
}
