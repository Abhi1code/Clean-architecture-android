package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelByPart;
import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelHeader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;

public class VolleyRequestParams {

    private final int method;
    private final String url;
    private String payLoad;
    private String bodyContentType;
    private String HeaderContentType;
    private ArrayList<ModelHeader> headerArrayList;
    private int requestTime;
    private Type responseType;
    private Map<String, ModelByPart> params;

    private VolleyRequestParams(int method, String url) {
        this.method = method;
        this.url = url;
    }

    public static VolleyRequestParams get(int method, @NonNull String url) {
        return new VolleyRequestParams(method, url);
    }

    @NonNull
    public int getMethod() {
        return method;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public VolleyRequestParams setPayLoad(String stringPayload) {
        this.payLoad = stringPayload;
        return this;
    }

    public <T> VolleyRequestParams setPayLoad(@NonNull Object objectPayload, Class<T> clazz) {
        this.payLoad = VolleyGson.get().toJson(objectPayload, VolleyGson.get().getTypeFromClazz(clazz));
        return this;
    }

    public String getBodyContentType() {
        return bodyContentType;
    }

    public VolleyRequestParams setBodyContentType(String bodyContentType) {
        this.bodyContentType = bodyContentType;
        return this;
    }

    public String getHeaderContentType() {
        return HeaderContentType;
    }

    public VolleyRequestParams setHeaderContentType(String headerContentType) {
        HeaderContentType = headerContentType;
        return this;
    }

    public ArrayList<ModelHeader> getHeaderArrayList() {
        return headerArrayList;
    }

    public VolleyRequestParams setHeaderArrayList(ArrayList<ModelHeader> headerArrayList) {
        this.headerArrayList = headerArrayList;
        return this;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public VolleyRequestParams setRequestTime(int requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public Type getResponseType() {
        return responseType;
    }

    public <T> VolleyRequestParams setResponseType(Class<T> clazz) {
        this.responseType = VolleyGson.get().getTypeFromClazz(clazz);
        return this;
    }

    public Map<String, ModelByPart> getParams() {
        return params;
    }

    public VolleyRequestParams setParams(Map<String, ModelByPart> params) {
        this.params = params;
        return this;
    }
}
