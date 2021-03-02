package com.mindorks.framework.mvvm.custom.remote;

import com.android.volley.Request;
import com.mindorks.framework.mvvm.custom.remote.entity.Userpojo;
import com.mindorks.framework.mvvm.custom.remote.livedata.JsonArrayRequestData;
import com.mindorks.framework.mvvm.custom.remote.livedata.JsonObjectRequestData;
import com.mindorks.framework.mvvm.custom.remote.livedata.MultipartRequestData;
import com.mindorks.framework.mvvm.custom.remote.livedata.StringRequestData;
import com.mindorks.framework.mvvm.custom.remote.volley.helpers.VolleyRequestParams;
import com.mindorks.framework.mvvm.data.model.db.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppRemoteHelper implements RemoteHelper {

    @Inject
    public AppRemoteHelper() {}

    @Override
    public StringRequestData<String> sampleStringRequest() {
        VolleyRequestParams params = VolleyRequestParams.get(Request.Method.GET, "url");
        return new StringRequestData<>(String.class, params);
    }

    @Override
    public MultipartRequestData<String> sampleMultipartRequest() {
        VolleyRequestParams params = VolleyRequestParams.get(Request.Method.GET, "url");
        MultipartRequestData<String> requestData = new MultipartRequestData<>(String.class, params);
        return requestData;
    }

    @Override
    public JsonObjectRequestData<Userpojo> sampleJsonObjectRequest() {
        VolleyRequestParams params = VolleyRequestParams.get(Request.Method.GET, "url")
                .setPayLoad("hkjhkjhbk", String.class)
                .setResponseType(Userpojo.class);
        JsonObjectRequestData<Userpojo> requestData = new JsonObjectRequestData<>(Userpojo.class, params);
        return requestData;
    }

    @Override
    public JsonArrayRequestData<Userpojo> sampleJsonArrayRequest() {
        VolleyRequestParams params = VolleyRequestParams.get(Request.Method.GET, "url")
                .setPayLoad("hkjhkjhbk", String.class)
                .setResponseType(Userpojo.class);
        return new JsonArrayRequestData<>(Userpojo.class, params);
    }
}
