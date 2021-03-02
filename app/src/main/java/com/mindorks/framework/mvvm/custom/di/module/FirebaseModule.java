package com.mindorks.framework.mvvm.custom.di.module;

import com.mindorks.framework.mvvm.custom.firebase.AppFirebaseHelper;
import com.mindorks.framework.mvvm.custom.firebase.FirebaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    @Singleton
    @Provides
    FirebaseHelper provideFirebaseHelper(AppFirebaseHelper appFirebaseHelper) {
        return appFirebaseHelper;
    }
}
