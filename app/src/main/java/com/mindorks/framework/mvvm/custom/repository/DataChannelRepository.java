package com.mindorks.framework.mvvm.custom.repository;

import com.mindorks.framework.mvvm.custom.rtc.DataChannelRtcDataSource;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class DataChannelRepository {

    @NonNull private final DataChannelRtcDataSource mDataChannelRtcDataSource;

    public DataChannelRepository(@NonNull DataChannelRtcDataSource dataChannelRtcDataSource) {
        mDataChannelRtcDataSource = dataChannelRtcDataSource;
    }

    public Observable<byte[]> onMessage() {
        return mDataChannelRtcDataSource.onMessage();
    }

    public Observable<Boolean> onDataChannelStateChange() {
        return mDataChannelRtcDataSource.onDataChannelStateChange();
    }
}
