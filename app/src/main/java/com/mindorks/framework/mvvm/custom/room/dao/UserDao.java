package com.mindorks.framework.mvvm.custom.room.dao;

import com.mindorks.framework.mvvm.custom.room.entity.Userpojo;
import com.mindorks.framework.mvvm.data.model.db.Option;
import com.mindorks.framework.mvvm.data.model.db.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    Single<Userpojo> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Userpojo user);

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<Userpojo> loadAllByIds();

    @Query("SELECT * FROM options")
    Single<List<Userpojo>> loadAll();
}
