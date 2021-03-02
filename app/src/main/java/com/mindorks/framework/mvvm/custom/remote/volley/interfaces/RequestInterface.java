package com.mindorks.framework.mvvm.custom.remote.volley.interfaces;

import com.android.volley.Request;

public interface RequestInterface {

    Request<?> makeRequest();

    void cancelRequest();
}
