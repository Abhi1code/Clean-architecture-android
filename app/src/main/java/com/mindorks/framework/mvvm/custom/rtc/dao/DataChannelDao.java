package com.mindorks.framework.mvvm.custom.rtc.dao;

import io.reactivex.Observable;

public interface DataChannelDao {

    Observable<byte[]> onMessage();

    Observable<Boolean> onDataChannelStateChange();
}
