package com.mindorks.framework.mvvm.custom.rtc;

import android.util.Log;

import timber.log.Timber;

public class TimberLogTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        switch (priority) {
            case Log.VERBOSE:
                v(t, message, tag);
                break;
            case Log.DEBUG:
                d(t, message, tag);
                break;
            case Log.INFO:
                i(t, message, tag);
                break;
            case Log.WARN:
                w(t, message, tag);
                break;
            case Log.ERROR:
                e(t, message, tag);
                break;
        }
    }

    @Override
    protected boolean isLoggable(String tag, int priority) {
        return priority == Log.DEBUG || priority == Log.ERROR;
    }
}
