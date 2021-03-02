package com.mindorks.framework.mvvm.custom.rtc;

import com.mindorks.framework.mvvm.custom.rtc.dao.ConnectionDao;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ConnectionRtcDataSource {

    @NonNull
    private final ConnectionDao mConnectionDao;

    public ConnectionRtcDataSource(@NonNull ConnectionDao connectionDao) {
        if (connectionDao == null)
            throw new IllegalArgumentException("Connection Dao should not be null");
        mConnectionDao = connectionDao;
    }

    public Single<Boolean> initiateConnectionAsInitiator() {
        return mConnectionDao.initiateConnectionAsInitiator();
    }

    public Single<Boolean> initiateConnectionAsAcceptor() {
        return mConnectionDao.initiateConnectionAsAcceptor();
    }

    public Observable<BaseIceCandidateData> addIceCandidateListener() {
        return mConnectionDao.addIceCandidateListener();
    }

    public Observable<BaseSessionDescData> addSessionDescListener() {
        return mConnectionDao.addSessionDescListener();
    }

    public Observable<Boolean> addConnectionStateChangeListener() {
        return mConnectionDao.addConnectionStateChangeListener();
    }

    public Observable<Exception> addErrorListener() {
        return mConnectionDao.addErrorListener();
    }

    public Single<Boolean> closeConnection() {
        return mConnectionDao.closeConnection();
    }
}
