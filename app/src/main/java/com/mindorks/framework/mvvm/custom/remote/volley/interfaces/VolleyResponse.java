package com.mindorks.framework.mvvm.custom.remote.volley.interfaces;

import com.android.volley.VolleyError;

public interface VolleyResponse {

    interface Response<T> {
        void onResponse(T response);
    }

    interface StatusListener {
        void onStatusCodeResponse(Integer statusCode);
    }

    interface ErrorListener {
        void onErrorResponse(VolleyError error, int statusCode, String errorMessage);
    }
}
