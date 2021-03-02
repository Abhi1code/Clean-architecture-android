package com.mindorks.framework.mvvm.custom.remote.volley.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyListenerHandlers;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyRequestParams;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyUtils;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.RequestInterface;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.VolleyResponse;
import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class VolleyJsonArrayRequest extends JsonRequest<JSONArray> implements RequestInterface {

    private final String mbodyContentType;
    private final String mHeaderContentType;
    private final ArrayList<ModelHeader> mheaderArrayList;

    public VolleyJsonArrayRequest(final VolleyRequestParams volleyRequestParams,
                                   final VolleyResponse.Response<Object> responseListener,
                                   final VolleyResponse.ErrorListener errorListener) {

        super(volleyRequestParams.getMethod(),
                volleyRequestParams.getUrl(),
                volleyRequestParams.getPayLoad(),
                VolleyListenerHandlers.arrayListenerHandler(responseListener, volleyRequestParams.getResponseType()),
                VolleyListenerHandlers.errorListenerHandler(errorListener));

        this.mbodyContentType = volleyRequestParams.getBodyContentType();
        this.mHeaderContentType = volleyRequestParams.getHeaderContentType();
        this.mheaderArrayList = volleyRequestParams.getHeaderArrayList();
        int mrequestTime = volleyRequestParams.getRequestTime();

        setRetryPolicy(VolleyUtils.get().getRetryPolicy(mrequestTime));
    }

    @Override
    public Map<String, String> getHeaders() {
        return VolleyUtils.get().getVolleyHeaders(mHeaderContentType, mheaderArrayList);
    }

    @Override
    public String getBodyContentType() {
        return VolleyUtils.get().getBodyContentType(mbodyContentType, null);
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
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
