package com.mindorks.framework.mvvm.custom.firebase;

import com.mindorks.framework.mvvm.custom.firebase.entity.Userpojo;
import com.mindorks.framework.mvvm.custom.firebase.livedata.ProlongedValueData;
import com.mindorks.framework.mvvm.custom.firebase.livedata.SingleValueData;
import com.mindorks.framework.mvvm.custom.firebase.livedata.TaskValueData;

import androidx.annotation.NonNull;

public interface FirebaseHelper {

    ProlongedValueData<Userpojo> listenForChanges();

    SingleValueData<Userpojo> listenForSingleChange();

    TaskValueData<Boolean> updateTask();
}
