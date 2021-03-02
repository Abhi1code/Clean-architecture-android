package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import com.mindorks.framework.mvvm.custom.remote.volley.request.VolleyJsonArrayRequest;
import com.mindorks.framework.mvvm.custom.remote.volley.request.VolleyJsonObjectRequest;
import com.mindorks.framework.mvvm.custom.remote.volley.request.VolleyMultipartRequest;
import com.mindorks.framework.mvvm.custom.remote.volley.request.VolleyStringRequest;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.VolleyResponse;

public class VolleyRequest {

    public static VolleyStringRequest createVolleyStringRequest(VolleyRequestParams volleyRequestParams,
                                                                VolleyResponse.Response<String> responseListener,
                                                                VolleyResponse.ErrorListener errorListener) {
        return new VolleyStringRequest(volleyRequestParams, responseListener, errorListener);
    }

    public static VolleyJsonObjectRequest createVolleyJsonObjectRequest(VolleyRequestParams volleyRequestParams,
                                                                VolleyResponse.Response<Object> responseListener,
                                                                VolleyResponse.ErrorListener errorListener) {
        return new VolleyJsonObjectRequest(volleyRequestParams, responseListener, errorListener);
    }

    public static VolleyJsonArrayRequest createVolleyJsonArrayRequest(VolleyRequestParams volleyRequestParams,
                                                                VolleyResponse.Response<Object> responseListener,
                                                                VolleyResponse.ErrorListener errorListener) {
        return new VolleyJsonArrayRequest(volleyRequestParams, responseListener, errorListener);
    }

    public static VolleyMultipartRequest createVolleyMultipartRequest(VolleyRequestParams volleyRequestParams,
                                                                VolleyResponse.Response<String> responseListener,
                                                                VolleyResponse.ErrorListener errorListener) {
        return new VolleyMultipartRequest(volleyRequestParams, responseListener, errorListener);
    }
}
