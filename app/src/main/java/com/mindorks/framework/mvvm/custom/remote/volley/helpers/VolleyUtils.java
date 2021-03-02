package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.mindorks.framework.mvvm.custom.remote.volley.VolleySingleton;
import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VolleyUtils {

    private static VolleyUtils volleyUtils;
    private ArrayList<ModelHeader> headerArrayList;

    public static VolleyUtils get() {
        if (volleyUtils == null) {
            volleyUtils = new VolleyUtils();
        }
        return volleyUtils;
    }

    public boolean checkResponseCode(@NonNull Integer networkResponse) {
        return networkResponse > 199 && networkResponse < 300;
    }

    public Map<String, String> getVolleyHeaders(@Nullable String headerContentType,
                                                @Nullable ArrayList<ModelHeader> carray) {
        Map<String, String> headers = new HashMap<>();
        if (VolleyConstants.get().isEnableHeaderContentType()) {
            if (headerContentType == null || headerContentType.isEmpty())
                headers.put("Content-Type", headerContentType);
            else
                headers.put("Content-Type", VolleyConstants.get().getHeaderContentType());
        }

        if (carray == null || carray.isEmpty())
            headers = addHeaders(headers, headerArrayList);
        else
            headers = addHeaders(headers, carray);

        return headers;
    }

    private Map<String, String> addHeaders(Map<String, String> headers, ArrayList<ModelHeader> headerList) {
        if (headerList != null && !headerList.isEmpty()) {
            for (ModelHeader modelHeader : headerList) {
                headers.put(modelHeader.getKey(), modelHeader.getValue());
            }
        }
        return headers;
    }

    @NonNull
    public ArrayList<ModelHeader> getUpHeaders() {
        if (headerArrayList == null) {
            headerArrayList = new ArrayList<>();
        }
        return headerArrayList;
    }

    public void setUpHeaders(@NonNull ArrayList<ModelHeader> arrayList) {
        headerArrayList = arrayList;
    }

    public String getBodyContentType(@Nullable String contentType, @Nullable String customType) {
        // Must be in order
        if (contentType != null && !contentType.isEmpty()) return contentType;
        if (customType != null && !customType.isEmpty()) return customType;
        return VolleyConstants.get().getBodyContentType();
    }

    public RetryPolicy getRetryPolicy(int requestTime) {
        int tempRequestTime = requestTime;
        if (requestTime <= 0) tempRequestTime = VolleyConstants.get().getDefaultRequestTime();
        return new DefaultRetryPolicy(
                tempRequestTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public void addToRequestQueue(Request<?> request) {
        VolleySingleton.get().getRequestQueue().add(request);
    }
}
