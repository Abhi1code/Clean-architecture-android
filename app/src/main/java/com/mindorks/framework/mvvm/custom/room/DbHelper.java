package com.mindorks.framework.mvvm.custom.room;

import com.mindorks.framework.mvvm.custom.room.entity.Userpojo;
import com.mindorks.framework.mvvm.data.model.db.Option;
import com.mindorks.framework.mvvm.data.model.db.User;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<List<Userpojo>> getAllUsers();

    Observable<List<Userpojo>> getOptionsForQuestionId(Long questionId);

    Observable<Boolean> insertUser(final Userpojo user);
}
