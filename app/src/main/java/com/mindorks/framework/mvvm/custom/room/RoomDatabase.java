package com.mindorks.framework.mvvm.custom.room;

import com.mindorks.framework.mvvm.custom.remote.entity.Userpojo;
import com.mindorks.framework.mvvm.custom.room.dao.UserDao;

import androidx.room.Database;

@Database(entities = {Userpojo.class}, version = 2)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract UserDao userDao();
}
