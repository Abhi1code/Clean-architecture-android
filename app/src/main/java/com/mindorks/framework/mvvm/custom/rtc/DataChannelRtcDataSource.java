package com.mindorks.framework.mvvm.custom.rtc;

import com.mindorks.framework.mvvm.custom.rtc.dao.DataChannelDao;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class DataChannelRtcDataSource {

    @NonNull
    private final DataChannelDao mDataChannelDao;

    public DataChannelRtcDataSource(@NonNull DataChannelDao dataChannelDao) {
        if (dataChannelDao == null)
            throw new IllegalArgumentException("DataChannel Dao should not be null");
        mDataChannelDao = dataChannelDao;
    }

    public Observable<byte[]> onMessage() {
        return mDataChannelDao.onMessage();
    }

    public Observable<Boolean> onDataChannelStateChange() {
        return mDataChannelDao.onDataChannelStateChange();
    }
}
