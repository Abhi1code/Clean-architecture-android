package com.mindorks.framework.mvvm.custom.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class RoomPreferences implements RoomPrefDataSource {

    @NonNull private final SharedPreferences mPrefs;

    public static final String ROOM_SESSION = "ROOM_SESSION";
    public static final String ROOM_ID = "ROOM_ID";

    public RoomPreferences(@NonNull Context context, @NonNull String fileName) {
        mPrefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setRoomSession(boolean flag) {
        mPrefs.edit().putBoolean(ROOM_SESSION, flag).apply();
    }

    @Override
    public boolean getRoomSession() {
        return mPrefs.getBoolean(ROOM_SESSION, false);
    }

    @Override
    public void setRoomId(@NonNull String roomId) {
        mPrefs.edit().putString(ROOM_ID, roomId).apply();
    }

    @Override
    public String getRoomId() {
        return mPrefs.getString(ROOM_ID, null);
    }

    @Override
    public void resetAll() {
        mPrefs.edit().clear().apply();
    }
}
