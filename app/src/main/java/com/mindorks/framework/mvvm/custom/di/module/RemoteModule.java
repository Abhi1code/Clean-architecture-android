package com.mindorks.framework.mvvm.custom.di.module;

import com.mindorks.framework.mvvm.custom.remote.AppRemoteHelper;
import com.mindorks.framework.mvvm.custom.remote.RemoteHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteModule {

    @Singleton
    @Provides
    RemoteHelper provideRemoteHelper(AppRemoteHelper appRemoteHelper) {
        return appRemoteHelper;
    }
}
