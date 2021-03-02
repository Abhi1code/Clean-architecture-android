package com.mindorks.framework.mvvm.custom.firebase.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.dao.SdpFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.model.SessionDescFirebaseData;
import com.mindorks.framework.mvvm.custom.firebase.rx.BaseRxFirebase;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class SdpFirebaseDaoImpl implements SdpFirebaseDao {

    @NonNull
    private final BaseRxFirebase baseRxFirebase;

    public SdpFirebaseDaoImpl(@NonNull BaseRxFirebase baseRxFirebase) {
        this.baseRxFirebase = baseRxFirebase;
    }

    @NonNull
    @Override
    public Observable<SessionDescFirebaseData> getSdp(@NonNull Query query) {
        return baseRxFirebase.getObservableForSingleEvent(query, SessionDescFirebaseData.class);
    }

    @NonNull
    @Override
    public Observable<SessionDescFirebaseData> addSdpListener(@NonNull Query query) {
        return baseRxFirebase.getObservable(query, SessionDescFirebaseData.class);
    }

    @Override
    public void insertSdp(@NonNull DatabaseReference databaseReference,
                          @NonNull SessionDescFirebaseData sessionDescFirebaseData) {
        baseRxFirebase.insertData(databaseReference, sessionDescFirebaseData);
    }
}
