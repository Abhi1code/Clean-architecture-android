package com.mindorks.framework.mvvm.custom.room;

import com.mindorks.framework.mvvm.custom.room.entity.Userpojo;
import com.mindorks.framework.mvvm.data.local.db.AppDatabase;
import com.mindorks.framework.mvvm.data.model.db.Option;
import com.mindorks.framework.mvvm.data.model.db.User;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AppDbHelper implements DbHelper {

    private final RoomDatabase roomDatabase;

    @Inject
    public AppDbHelper(RoomDatabase roomDatabase) {
        this.roomDatabase = roomDatabase;
    }

    @Override
    public Observable<List<Userpojo>> getAllUsers() {
        return roomDatabase.userDao().loadAll()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Userpojo>> getOptionsForQuestionId(Long questionId) {
        return Observable.fromCallable(new Callable<List<Userpojo>>() {
            @Override
            public List<Userpojo> call() throws Exception {
                return roomDatabase.userDao().loadAllByIds();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Boolean> insertUser(Userpojo user) {
        return roomDatabase.userDao().loadAll()
                .flatMapObservable(new Function<List<Userpojo>, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(@NonNull List<Userpojo> options) throws Exception {
                        return Observable.just(options.isEmpty());
                    }
                });
    }
}
