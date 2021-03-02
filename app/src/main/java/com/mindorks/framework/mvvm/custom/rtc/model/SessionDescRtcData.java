package com.mindorks.framework.mvvm.custom.rtc.model;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;

public class SessionDescRtcData implements BaseSessionDescData {

    private final String sdp;
    private final String type;

    public SessionDescRtcData(String sdp, String type) {
        this.sdp = sdp;
        this.type = type;
    }

    @Override
    public String getSdp() {
        return sdp;
    }

    @Override
    public String getType() {
        return type;
    }
}
