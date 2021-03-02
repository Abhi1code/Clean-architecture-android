package com.mindorks.framework.mvvm.custom.remote.volley.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyConstants;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyListenerHandlers;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyRequestParams;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyUtils;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.RequestInterface;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.VolleyResponse;
import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelByPart;
import com.mindorks.framework.mvvm.custom.remote.volley.model.ModelHeader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;


public class VolleyMultipartRequest extends Request<String> implements RequestInterface {

    private final String mRequestBody;
    private final String mbodyContentType;
    private final String mHeaderContentType;
    private final ArrayList<ModelHeader> mheaderArrayList;
    private final VolleyResponse.Response<String> mListener;

    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    private final String boundary = "*****";
    private Map<String, ModelByPart> params;

    public VolleyMultipartRequest(final VolleyRequestParams volleyRequestParams,
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
        this.params = volleyRequestParams.getParams();
        int mrequestTime = volleyRequestParams.getRequestTime();

        setRetryPolicy(VolleyUtils.get().getRetryPolicy(mrequestTime));
    }

    @Override
    public String getBodyContentType() {
        return VolleyUtils.get().getBodyContentType(mbodyContentType, "multipart/form-data;boundary=" + boundary);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            Map<String, String> params = getParams();
            if (params != null && params.size() > 0) {
                textParse(dos, params, getParamsEncoding());
            }
            Map<String, ModelByPart> data = getByteData();
            if (data != null && data.size() > 0) {
                dataParse(dos, data);
            }
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            return bos.toByteArray();
        } catch (IOException ignored) {
        }
        return null;
    }

    protected Map<String, ModelByPart> getByteData() {
        return params;
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
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
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

    private void textParse(DataOutputStream dataOutputStream, Map<String, String> params, String encoding) throws IOException {
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buildTextPart(dataOutputStream, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + encoding, uee);
        }
    }

    private void dataParse(DataOutputStream dataOutputStream, Map<String, ModelByPart> data) throws IOException {
        for (Map.Entry<String, ModelByPart> entry : data.entrySet()) {
            buildDataPart(dataOutputStream, entry.getValue(), entry.getKey());
        }
    }

    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);
    }

    private void buildDataPart(DataOutputStream dataOutputStream, ModelByPart dataFile, String inputName) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                inputName + "\"; filename=\"" + dataFile.getFileName() + "\"" + lineEnd);
        if (dataFile.getType() != null && !dataFile.getType().trim().isEmpty()) {
            dataOutputStream.writeBytes("Content-Type: " + dataFile.getType() + lineEnd);
        }
        dataOutputStream.writeBytes(lineEnd);
        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(dataFile.getContent());
        int bytesAvailable = fileInputStream.available();

        int bufferSize = Math.min(bytesAvailable, VolleyConstants.get().getMaxBufferSize());
        byte[] buffer = new byte[bufferSize];

        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, VolleyConstants.get().getMaxBufferSize());
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }
}
