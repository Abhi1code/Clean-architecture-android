package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class VolleyGson {

    private Gson gson;
    private static VolleyGson volleyGson;

    public static VolleyGson get() {
        if (volleyGson == null) {
            return new VolleyGson();
        }
        return volleyGson;
    }

    private VolleyGson() {
        this.setVolleyGSON();
    }

    public void setVolleyGSON() {
//        gson = new GsonBuilder()
//                .setExclusionStrategies(new AnnotationExclusionStrategy()).create();
        gson = new GsonBuilder().create();
    }

    public void setVolleyGSONExcludeExpose() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation().create();
    }

    public <T> Type getTypeFromClazz(Class<T> clazz) {
        return new TypeToken<T>(){}.getType();
    }

    public String toJson(Object object, Type type) {
        return gson.toJson(object, type);
    }

    public Object fromJson(Type type, String data) {
        return gson.fromJson(data, type);
    }
}
