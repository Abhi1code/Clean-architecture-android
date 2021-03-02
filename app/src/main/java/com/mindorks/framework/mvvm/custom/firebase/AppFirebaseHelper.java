package com.mindorks.framework.mvvm.custom.firebase;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.mindorks.framework.mvvm.custom.firebase.entity.Userpojo;
import com.mindorks.framework.mvvm.custom.firebase.livedata.ProlongedValueData;
import com.mindorks.framework.mvvm.custom.firebase.livedata.SingleValueData;
import com.mindorks.framework.mvvm.custom.firebase.livedata.TaskValueData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppFirebaseHelper implements FirebaseHelper {

    private final FirebaseDatabase firebaseDatabase;

    @Inject
    public AppFirebaseHelper() {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
    }

    @Override
    public ProlongedValueData<Userpojo> listenForChanges() {
        Query query = this.firebaseDatabase.getReference().child("test");
        ProlongedValueData<Userpojo> prolongedValueData = new ProlongedValueData<>(Userpojo.class, query);
        return prolongedValueData;
    }

    @Override
    public SingleValueData<Userpojo> listenForSingleChange() {
        Query query = this.firebaseDatabase.getReference().child("test");
        SingleValueData<Userpojo> singleValueData = new SingleValueData<>(Userpojo.class, query);
        return singleValueData;
    }

    @Override
    public TaskValueData<Boolean> updateTask() {
        Task task = this.firebaseDatabase.getReference().child("test").setValue(1);
        TaskValueData<Boolean> taskValueData = new TaskValueData<>(Boolean.class, task);
        return taskValueData;
    }
}
