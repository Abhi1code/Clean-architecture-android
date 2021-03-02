package com.mindorks.framework.mvvm.custom.firebase.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.dao.HangupFirebaseDao;
import com.mindorks.framework.mvvm.custom.firebase.model.HangupFirebaseData;
import com.mindorks.framework.mvvm.custom.firebase.rx.BaseRxFirebase;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class HangupFirebaseDaoImpl implements HangupFirebaseDao {

    @NonNull private final BaseRxFirebase baseRxFirebase;

    public HangupFirebaseDaoImpl(@NonNull BaseRxFirebase baseRxFirebase) {
        this.baseRxFirebase = baseRxFirebase;
    }

    @NonNull
    @Override
    public Observable<HangupFirebaseData> getHangupFlag(@NonNull Query query) {
        return baseRxFirebase.getObservableForSingleEvent(query, HangupFirebaseData.class);
    }

    @NonNull
    @Override
    public Observable<HangupFirebaseData> addHangupListener(@NonNull Query query) {
        return baseRxFirebase.getObservable(query, HangupFirebaseData.class);
    }

    @Override
    public void updateHangupFlag(@NonNull DatabaseReference databaseReference,
                                 @NonNull HangupFirebaseData hangupFirebaseData) {
        baseRxFirebase.insertData(databaseReference, hangupFirebaseData);
    }
}
