package com.mindorks.framework.mvvm.custom.firebase.rx;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public abstract class BaseRxFirebase {

    public BaseRxFirebase() { }

    /*
     * Observable for firebase query single value event
     * @param query
     * @param clazz
     * @param <T>
     * @return onNext if query is success and onError if query is failed
     */
    @NonNull public abstract <T> Observable<T> getObservableForSingleEvent(@NonNull final Query query,
                                                                           @NonNull final Class<T> clazz);

    /*
     * Observable for firebase query for value event
     * @param query
     * @param clazz
     * @param <T>
     * @return onNext if query is success and onError if query is failed
     */
    @NonNull public abstract <T> Observable<T> getObservable(@NonNull final Query query,
                                                                           @NonNull final Class<T> clazz);

    /*
     * Observable for firebase task
     * @param task
     * @param <T>
     * @return onNext if task is success and onError if task is failed
     */
    @NonNull public abstract <T> Observable<T> getObservable(@NonNull final Task<T> task);

    /*
     * As RxJava 2 does not support Void (null) values as emitter item, we have to send an object back
     * @param task
     * @param objectToReturn
     * @param <T>
     * @return onNext if task is success and onError if task is failed
     */
    @NonNull public abstract <T> Observable<Object> getObservable(@NonNull final Task<T> task,
                                                                  @NonNull final Object objectToReturn);

    /*
    * Insert data to firebase database
    * @param databaseReference
    * @param data
    */
    public void insertData(@NonNull final DatabaseReference databaseReference, @NonNull final Object data) {
        databaseReference.setValue(data);
    }
}
