package com.mindorks.framework.mvvm.custom.firebase.rx;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mindorks.framework.mvvm.custom.firebase.exception.FirebaseDataCastException;
import com.mindorks.framework.mvvm.custom.firebase.exception.FirebaseDataException;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

public class DefaultRxFirebase extends BaseRxFirebase {

    public DefaultRxFirebase() {
        super();
    }

    @NonNull
    @Override
    public <T> Observable<T> getObservableForSingleEvent(@NonNull Query query, @NonNull Class<T> clazz) {
        return Observable.create(emitter -> query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                T value = snapshot.getValue(clazz);
                if (value != null) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(value);
                    }
                } else {
                    if (!emitter.isDisposed()) {
                        emitter.onError(new FirebaseDataCastException("Unable to cast Firebase data response to " + clazz.getSimpleName()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (!emitter.isDisposed()) {
                    emitter.onError(new FirebaseDataException(error));
                }
            }
        }));
    }

    @NonNull
    @Override
    public <T> Observable<T> getObservable(@NonNull Query query, @NonNull Class<T> clazz) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> emitter) throws Exception {

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        T value = snapshot.getValue(clazz);
                        if (value != null) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(value);
                            }
                        } else {
                            query.removeEventListener(this);
                            if (!emitter.isDisposed()) {
                                emitter.onError(new FirebaseDataCastException("Unable to cast Firebase data response to " + clazz.getSimpleName()));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        query.removeEventListener(this);
                        if (!emitter.isDisposed()) {
                            emitter.onError(new FirebaseDataException(error));
                        }
                    }
                };

                query.addValueEventListener(valueEventListener);

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        query.removeEventListener(valueEventListener);
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public <T> Observable<T> getObservable(@NonNull Task<T> task) {
        return Observable.create(emitter -> task.addOnSuccessListener(t -> {
            if (!emitter.isDisposed()) {
                emitter.onNext(t);
            }
        }).addOnFailureListener(e -> {
            if (!emitter.isDisposed()) {
                emitter.onError(e);
            }
        }));
    }

    @NonNull
    @Override
    public <T> Observable<Object> getObservable(@NonNull Task<T> task, @NonNull Object objectToReturn) {
        return Observable.create(emitter -> task.addOnSuccessListener(result -> {
            if (!emitter.isDisposed()) {
                if ((result instanceof Void
                        || result == null)
                        && objectToReturn != null) {
                    emitter.onNext(objectToReturn);
                } else {
                    emitter.onNext(result);
                }
            }
        }).addOnFailureListener(error -> {
            if (!emitter.isDisposed()) {
                emitter.onError(error);
            }
        }));
    }
}
