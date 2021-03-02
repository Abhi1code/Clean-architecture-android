package com.mindorks.framework.mvvm.custom.di.component;

import com.mindorks.framework.mvvm.custom.di.module.FirebaseModule;
import com.mindorks.framework.mvvm.custom.di.module.RemoteModule;
import com.mindorks.framework.mvvm.custom.firebase.FirebaseHelper;
import com.mindorks.framework.mvvm.custom.remote.RemoteHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RemoteModule.class})
public interface RemoteComponent {

    RemoteHelper getRemoteHelper();
}
