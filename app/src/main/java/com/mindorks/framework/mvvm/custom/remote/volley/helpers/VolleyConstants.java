package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import androidx.annotation.NonNull;

public class VolleyConstants {

    private static VolleyConstants volleyConstants;

    @NonNull
    public static String PROTOCOL_CHARSET = "utf-8";
    private int maxBufferSize = 1024 * 1024 * 10;
    private int defaultRequestTime = 10000;
    private int defaultRequestTimeMax = 12000;
    private String headerContentType = "application/json; charset=utf-8";
    private String bodyContentType = "application/json";
    private boolean enableHeaderContentType;

    public static VolleyConstants get() {
        if (volleyConstants == null) {
            volleyConstants = new VolleyConstants();
        }
        return volleyConstants;
    }

    public int getDefaultRequestTime() {
        return defaultRequestTime;
    }

    public void setDefaultRequestTime(@NonNull int defaultRequestTime) {
        this.defaultRequestTime = defaultRequestTime;
    }

    public int getDefaultRequestTimeMax() {
        return defaultRequestTimeMax;
    }

    public void setDefaultRequestTimeMax(@NonNull int defaultRequestTimeMax) {
        this.defaultRequestTimeMax = defaultRequestTimeMax;
    }

    public String getHeaderContentType() {
        return headerContentType;
    }

    public void setHeaderContentType(@NonNull String headerContentType) {
        this.setEnableHeaderContentType(true);
        this.headerContentType = headerContentType;
    }

    public boolean isEnableHeaderContentType() {
        return enableHeaderContentType;
    }

    public void setEnableHeaderContentType(boolean enableHeaderContentType) {
        this.enableHeaderContentType = enableHeaderContentType;
    }

    public String getBodyContentType() {
        return bodyContentType;
    }

    public void setBodyContentType(@NonNull String bodyContentType) {
        this.bodyContentType = bodyContentType;
    }

    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public void setMaxBufferSize(int maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
    }
}
