package com.mindorks.framework.mvvm.custom.rtc.dao;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ConnectionDao {

    Single<Boolean> initiateConnectionAsInitiator();

    Single<Boolean> initiateConnectionAsAcceptor();

    Observable<BaseIceCandidateData> addIceCandidateListener();

    Observable<BaseSessionDescData> addSessionDescListener();

    Observable<Boolean> addConnectionStateChangeListener();

    Observable<Exception> addErrorListener();

    Single<Boolean> closeConnection();
}
