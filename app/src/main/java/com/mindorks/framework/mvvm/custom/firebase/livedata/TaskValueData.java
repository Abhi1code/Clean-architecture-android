package com.mindorks.framework.mvvm.custom.firebase.livedata;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mindorks.framework.mvvm.custom.common.StateData;
import com.mindorks.framework.mvvm.custom.firebase.livedata.livedatainterface.FirebaseLiveDataInterface;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class TaskValueData<T> extends LiveData<StateData<T>>
        implements FirebaseLiveDataInterface {

    private final Class<T> clazz;
    private final Task task;

    private final MyTaskSuccessListener myTaskSuccessListener = new MyTaskSuccessListener();
    private final MyTaskFailureListener myTaskFailureListener = new MyTaskFailureListener();

    public TaskValueData(@NonNull final Class<T> clazz, @NonNull final Task task) {
        this.clazz = clazz;
        this.task = task;
        this.task.addOnSuccessListener(myTaskSuccessListener)
                .addOnFailureListener(myTaskFailureListener);
    }

    @Override
    protected void onActive() {
    }

    @Override
    protected void onInactive() {
    }

    @Override
    public void removeListener() {
    }

    private class MyTaskSuccessListener implements OnSuccessListener {
        @Override
        public void onSuccess(Object o) {
            setValue(new StateData<T>().complete());
        }
    }

    private class MyTaskFailureListener implements OnFailureListener {
        @Override
        public void onFailure(@NonNull Exception e) {
            setValue(new StateData<T>().error(e));
        }
    }
}
