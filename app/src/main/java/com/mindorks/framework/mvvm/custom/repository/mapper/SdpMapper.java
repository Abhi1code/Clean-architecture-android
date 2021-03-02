package com.mindorks.framework.mvvm.custom.repository.mapper;

import com.mindorks.framework.mvvm.custom.firebase.model.SessionDescFirebaseData;
import com.mindorks.framework.mvvm.custom.rtc.interfaces.BaseSessionDescData;
import com.mindorks.framework.mvvm.custom.rtc.model.SessionDescRtcData;

import androidx.annotation.NonNull;

public class SdpMapper {

    @NonNull
    public static SessionDescFirebaseData toFirebaseSdp(@NonNull BaseSessionDescData baseSessionDescData) {
        return new SessionDescFirebaseData(baseSessionDescData.getSdp(), baseSessionDescData.getType());
    }

    @NonNull
    public static BaseSessionDescData toRtcSdp(@NonNull SessionDescFirebaseData sessionDescFirebaseData) {
        return new SessionDescRtcData(sessionDescFirebaseData.getSdp(), sessionDescFirebaseData.getType());
    }
}
