package com.mindorks.framework.mvvm.custom.rtc.source;

import android.content.Context;

import com.mindorks.framework.mvvm.custom.rtc.source.callback.RtcCallbackProvider;
import com.mindorks.framework.mvvm.custom.rtc.dao.ConnectionDao;
import com.mindorks.framework.mvvm.custom.rtc.dao.DataChannelDao;
import com.mindorks.framework.mvvm.custom.rtc.dao.MediaDao;
import com.mindorks.framework.mvvm.custom.rtc.utils.IceServerData;
import com.mindorks.framework.mvvm.custom.rtc.utils.SdpData;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.BaseRtcClient;
import com.mindorks.framework.mvvm.custom.rtc.webrtc.DefaultRtcClient;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class RtcImpl {

    private volatile MediaDao mediaDao;
    private volatile ConnectionDao connectionDao;
    private volatile DataChannelDao dataChannelDao;

    @NonNull private final BaseRtcClient baseRtcClient;
    @NonNull private final RtcCallbackProvider rtcCallbackProvider;

    private RtcImpl(@NonNull BaseRtcClient baseRtcClient,
                    @NonNull RtcCallbackProvider rtcCallbackProvider) {
        this.baseRtcClient = baseRtcClient;
        this.rtcCallbackProvider = rtcCallbackProvider;
    }

    public MediaDao mediaDao() {
        if (mediaDao != null) {
            return mediaDao;
        } else {
            synchronized (this) {
                if (mediaDao == null) {
                    mediaDao = new MediaDaoImpl(baseRtcClient, rtcCallbackProvider);
                }
                return mediaDao;
            }
        }
    }

    public DataChannelDao dataChannelDao() {
        if (dataChannelDao != null) {
            return dataChannelDao;
        } else {
            synchronized (this) {
                if (dataChannelDao == null) {
                    dataChannelDao = new DataChannelDaoImpl(baseRtcClient, rtcCallbackProvider);
                }
                return dataChannelDao;
            }
        }
    }

    public ConnectionDao connectionDao() {
        if (connectionDao != null) {
            return connectionDao;
        } else {
            synchronized (this) {
                if (connectionDao == null) {
                    connectionDao = new ConnectionDaoImpl(baseRtcClient, rtcCallbackProvider);
                }
                return connectionDao;
            }
        }
    }

    public static final class Builder {

        @NonNull private List<IceServerData> iceServerData;
        @NonNull private List<SdpData> sdpData;
        @NonNull private final Context context;

        public Builder(@NonNull Context context) {
            if (context == null)
                throw new IllegalArgumentException("Context should not be null");

            this.context = context.getApplicationContext();
            iceServerData = new ArrayList<>();
            sdpData = new ArrayList<>();
        }

        public Builder addIceServerData(List<IceServerData> iceServerData) {
            if (iceServerData != null) this.iceServerData = iceServerData;
            return this;
        }

        public Builder addSdpData(List<SdpData> sdpData) {
            if (sdpData != null) this.sdpData = sdpData;
            return this;
        }

        public RtcImpl build() {
            final RtcCallbackProvider rtcCallbackProvider = new RtcCallbackProvider();
            BaseRtcClient baseRtcClient = new DefaultRtcClient(context, rtcCallbackProvider,
                    iceServerData, sdpData);
            return new RtcImpl(baseRtcClient, rtcCallbackProvider);
        }
    }
}