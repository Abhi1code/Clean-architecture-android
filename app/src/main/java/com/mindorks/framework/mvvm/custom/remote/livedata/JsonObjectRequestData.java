package com.mindorks.framework.mvvm.custom.remote.livedata;

import com.android.volley.VolleyError;
import com.mindorks.framework.mvvm.custom.common.StateData;
import com.mindorks.framework.mvvm.custom.remote.exception.RemoteCallException;
import com.mindorks.framework.mvvm.custom.remote.exception.RemoteDataCastException;
import com.mindorks.framework.mvvm.custom.remote.livedata.livedatainterface.RemoteLiveDataInterface;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyRequest;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyRequestParams;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.RequestInterface;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.VolleyResponse;
import com.mindorks.framework.mvvm.custom.remote.volley.request.VolleyJsonObjectRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class JsonObjectRequestData<T> extends LiveData<StateData<T>>
        implements RemoteLiveDataInterface {

    private final Class<T> clazz;
    private final VolleyRequestParams volleyRequestParams;
    private final RequestInterface requestInterface;

    private final MyTaskSuccessListener myTaskSuccessListener = new MyTaskSuccessListener();
    private final MyTaskErrorListener myTaskErrorListener = new MyTaskErrorListener();

    public JsonObjectRequestData(@NonNull final Class<T> clazz,
                             @NonNull final VolleyRequestParams volleyRequestParams) {
        this.clazz = clazz;
        this.volleyRequestParams = volleyRequestParams;
        this.requestInterface = VolleyRequest.createVolleyJsonObjectRequest(this.volleyRequestParams,
                this.myTaskSuccessListener, this.myTaskErrorListener);
        this.requestInterface.makeRequest();
    }

    @Override
    protected void onActive() {
    }

    @Override
    protected void onInactive() {
    }

    @Override
    public void cancelRequest() {
        if (requestInterface != null) requestInterface.cancelRequest();
    }

    private class MyTaskSuccessListener implements VolleyResponse.Response<Object> {
        @Override
        public void onResponse(Object response) {
            T value = clazz.isAssignableFrom(response.getClass()) ? clazz.cast(response) : null;
            if (value != null) {
                setValue(new StateData<T>().success(value));
            } else {
                setValue(new StateData<T>().error(new RemoteDataCastException("Unable to cast Remote data response to " +
                        clazz.getSimpleName())));
            }
        }
    }

    private class MyTaskErrorListener implements VolleyResponse.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error, int statusCode, String errorMessage) {
            setValue(new StateData<T>().error(new RemoteCallException(statusCode, errorMessage)));
        }
    }
}
