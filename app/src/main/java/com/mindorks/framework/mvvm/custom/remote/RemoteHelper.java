package com.mindorks.framework.mvvm.custom.remote;

import com.mindorks.framework.mvvm.custom.remote.entity.Userpojo;
import com.mindorks.framework.mvvm.custom.remote.livedata.JsonArrayRequestData;
import com.mindorks.framework.mvvm.custom.remote.livedata.JsonObjectRequestData;
import com.mindorks.framework.mvvm.custom.remote.livedata.MultipartRequestData;
import com.mindorks.framework.mvvm.custom.remote.livedata.StringRequestData;

public interface RemoteHelper {

    StringRequestData<String> sampleStringRequest();

    MultipartRequestData<String> sampleMultipartRequest();

    JsonObjectRequestData<Userpojo> sampleJsonObjectRequest();

    JsonArrayRequestData<Userpojo> sampleJsonArrayRequest();
}
