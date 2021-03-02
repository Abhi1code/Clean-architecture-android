package com.mindorks.framework.mvvm.custom.rtc.utils;

import androidx.annotation.NonNull;

public class SdpData {

    @NonNull private final String key;
    @NonNull private final String value;

    public SdpData(@NonNull String key, @NonNull String value) {
        this.key = key;
        this.value = value;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    @NonNull
    public String getValue() {
        return value;
    }
}
