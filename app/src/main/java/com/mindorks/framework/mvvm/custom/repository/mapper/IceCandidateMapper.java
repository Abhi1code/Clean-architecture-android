package com.mindorks.framework.mvvm.custom.repository.mapper;

import com.mindorks.framework.mvvm.custom.firebase.model.IceCandidateFirebaseData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseIceCandidateData;
import com.mindorks.framework.mvvm.custom.rtc.model.IceCandidateRtcData;

import androidx.annotation.NonNull;

public class IceCandidateMapper {

    @NonNull
    public static IceCandidateFirebaseData toFirebaseIceCandidate(@NonNull BaseIceCandidateData baseIceCandidateData) {
        return new IceCandidateFirebaseData(baseIceCandidateData.getSdp(),
                baseIceCandidateData.getSdpMid(), baseIceCandidateData.getSdpMLineIndex());
    }

    @NonNull
    public static BaseIceCandidateData toRtcIceCandidate(@NonNull IceCandidateFirebaseData iceCandidateFirebaseData) {
        return new IceCandidateRtcData(iceCandidateFirebaseData.getSdp(), iceCandidateFirebaseData.getSdpMid(),
                iceCandidateFirebaseData.getSdpMLineIndex());
    }
}
