package com.mindorks.framework.mvvm.custom.remote.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;

public class VolleySingleton {

    private static VolleySingleton instance = null;
    private final RequestQueue requestQueue;
    private final LruCache<String, Bitmap> lruCache;
    private static int lruCacheSize = 1024 * 1024 * 4;

    private VolleySingleton(Context context, int lruCacheSize) {
        VolleySingleton.lruCacheSize = lruCacheSize;
        lruCache = new LruCache<>(lruCacheSize);
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static VolleySingleton get() {
        return instance;
    }

    public static void setInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context, lruCacheSize);
        }
    }

    public static void setInstance(@NonNull Context context, @NonNull int lruCacheSize) {
        if (instance == null) {
            instance = new VolleySingleton(context, lruCacheSize);
        }
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void clearCache() {
        lruCache.evictAll();
    }

    public void clearCacheOfUrl(String url) {
        lruCache.remove(url);
    }
}
