package com.mindorks.framework.mvvm.custom.firebase.livedata;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mindorks.framework.mvvm.custom.firebase.entity.Basepojo;
import com.mindorks.framework.mvvm.custom.common.StateData;
import com.mindorks.framework.mvvm.custom.firebase.exception.FirebaseDataCastException;
import com.mindorks.framework.mvvm.custom.firebase.exception.FirebaseDataException;
import com.mindorks.framework.mvvm.custom.firebase.livedata.livedatainterface.FirebaseLiveDataInterface;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class ProlongedValueData<T> extends LiveData<StateData<T>>
        implements FirebaseLiveDataInterface {

    private final Class<T> clazz;
    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    public ProlongedValueData(@NonNull final Class<T> clazz, @NonNull final Query query) {
        this.clazz = clazz;
        this.query = query;
        this.query.addValueEventListener(this.listener);
    }

    public ProlongedValueData(@NonNull final Class<T> clazz, @NonNull final DatabaseReference ref) {
        this.clazz = clazz;
        this.query = ref;
        this.query.addValueEventListener(this.listener);
    }

    @Override
    protected void onActive() {
        //query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        this.query.removeEventListener(this.listener);
    }

    @Override
    public void removeListener() {
        this.query.removeEventListener(this.listener);
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            T value = dataSnapshot.getValue(clazz);
            if (value != null) {
                setValue(new StateData<T>().success(value));
            } else {
                setValue(new StateData<T>().error(new FirebaseDataCastException("Unable to cast Firebase data response to " +
                        clazz.getSimpleName())));
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            query.removeEventListener(listener);
            setValue(new StateData<T>().error(new FirebaseDataException(databaseError)));
        }
    }
}
