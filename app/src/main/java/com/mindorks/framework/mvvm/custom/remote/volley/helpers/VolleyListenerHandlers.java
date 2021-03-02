package com.mindorks.framework.mvvm.custom.remote.volley.helpers;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mindorks.framework.mvvm.custom.remote.volley.interfaces.VolleyResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VolleyListenerHandlers {

    public static Response.ErrorListener errorListenerHandler(@Nullable final VolleyResponse.ErrorListener errorListener) {
        return error -> setDefaultError(error, errorListener);
    }

    private static void setDefaultError(@Nullable VolleyError error, @Nullable VolleyResponse.ErrorListener errorListener) {
        if (errorListener != null && error != null)
            errorListener.onErrorResponse(error, VolleyExceptions.get().getVolleyErrorStatusCode(error),
                    VolleyExceptions.get().getVolleyErrorMessage(error));
    }

    public static Response.Listener<JSONObject> objectListenerHandler(final VolleyResponse.Response<Object> listener, final Type type) {
        return response -> {
            if (type != null) {
                setData(type, listener, response.toString());
            } else {
                setDefaultData(listener, response);
            }
        };
    }

    public static Response.Listener<JSONArray> arrayListenerHandler(final VolleyResponse.Response<Object> listener, final Type type) {
        return response -> {
            if (type != null) {
                setData(type, listener, response.toString());
            } else {
                setDefaultData(listener, response);
            }
        };
    }

    private static void setData(@NonNull Type type, @Nullable VolleyResponse.Response<Object> listener, @NonNull String response) {
        if (listener != null) {
            Object object = VolleyGson.get().fromJson(type, response);
            listener.onResponse(object);
        }
    }

    private static void setDefaultData(@Nullable VolleyResponse.Response<Object> listener, Object response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }
}
