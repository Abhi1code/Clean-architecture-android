package com.mindorks.framework.mvvm.custom.rtc.utils;

import com.mindorks.framework.mvvm.custom.common.IceServerType;

import androidx.annotation.NonNull;

public class IceServerData {

    @NonNull private final String uri;
    @NonNull private final String userName;
    @NonNull private final String passWord;
    @NonNull private final IceServerType iceServerType;

    public IceServerData(@NonNull String uri, @NonNull IceServerType iceServerType) {
        this.uri = uri;
        this.iceServerType = iceServerType;
        this.userName = "";
        this.passWord = "";
    }

    public IceServerData(@NonNull String uri, @NonNull IceServerType iceServerType,
                         @NonNull String userName, @NonNull String passWord) {
        this.uri = uri;
        this.iceServerType = iceServerType;
        this.userName = userName;
        this.passWord = passWord;
    }

    @NonNull
    public String getUri() {
        return uri;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    @NonNull
    public String getPassWord() {
        return passWord;
    }

    @NonNull
    public IceServerType getIceServerType() {
        return iceServerType;
    }
}
