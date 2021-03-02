package com.mindorks.framework.mvvm.custom.di.component;

import com.mindorks.framework.mvvm.custom.di.module.FirebaseModule;
import com.mindorks.framework.mvvm.custom.firebase.FirebaseHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FirebaseModule.class})
public interface FirebaseComponent {

    FirebaseHelper getFirebaseHelper();
}
