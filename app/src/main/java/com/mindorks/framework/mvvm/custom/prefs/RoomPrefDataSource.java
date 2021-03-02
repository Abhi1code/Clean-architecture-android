package com.mindorks.framework.mvvm.custom.prefs;

import androidx.annotation.NonNull;

public interface RoomPrefDataSource {

    void setRoomSession(boolean flag);

    boolean getRoomSession();

    void setRoomId(@NonNull String roomId);

    String getRoomId();

    void resetAll();
}
