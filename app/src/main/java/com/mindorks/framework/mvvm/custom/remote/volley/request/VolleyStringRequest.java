package com.mindorks.framework.mvvm.custom.remote.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyConstants;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyListenerHandlers;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyRequestParams;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyUtils;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.RequestInterface;
import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelHeader;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.VolleyResponse;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class VolleyStringRequest extends Request<String> implements RequestInterface {

    private final String mRequestBody;
    private final String mbodyContentType;
    private final String mHeaderContentType;
    private final ArrayList<ModelHeader> mheaderArrayList;
    private final VolleyResponse.Response<String> mListener;

    public VolleyStringRequest(final VolleyRequestParams volleyRequestParams,
                               final VolleyResponse.Response<String> responseListener,
                               final VolleyResponse.ErrorListener errorListener) {
        super(volleyRequestParams.getMethod(),
                volleyRequestParams.getUrl(),
                VolleyListenerHandlers.errorListenerHandler(errorListener));

        this.mListener = responseListener;
        this.mRequestBody = volleyRequestParams.getPayLoad();
        this.mbodyContentType = volleyRequestParams.getBodyContentType();
        this.mHeaderContentType = volleyRequestParams.getHeaderContentType();
        this.mheaderArrayList = volleyRequestParams.getHeaderArrayList();
        int mrequestTime = volleyRequestParams.getRequestTime();

        setRetryPolicy(VolleyUtils.get().getRetryPolicy(mrequestTime));
    }

    @Override
    public String getBodyContentType() {
        return VolleyUtils.get().getBodyContentType(mbodyContentType, null);
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(VolleyConstants.PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, VolleyConstants.PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return VolleyUtils.get().getVolleyHeaders(mHeaderContentType, mheaderArrayList);
    }

    @Override
    protected void deliverResponse(String response) {
        if (mListener != null) mListener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Request<?> makeRequest() {
        VolleyUtils.get().addToRequestQueue(this);
        return this;
    }

    @Override
    public void cancelRequest() {
        cancel();
    }
}
