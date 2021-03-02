package com.mindorks.framework.mvvm.custom.rtc.model;

import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;

public class IceCandidateRtcData implements BaseIceCandidateData {

    private final String sdp;
    private final String sdpMid;
    private final int sdpMLineIndex;

    public IceCandidateRtcData(String sdp, String sdpMid, int sdpMLineIndex) {
        this.sdp = sdp;
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
    }

    @Override
    public String getSdp() {
        return sdp;
    }

    @Override
    public String getSdpMid() {
        return sdpMid;
    }

    @Override
    public int getSdpMLineIndex() {
        return sdpMLineIndex;
    }
}
