package com.max.news.base;

import android.app.Application;

import com.max.news.db.cache.Repository;
import com.orhanobut.hawk.Hawk;

/**
 * @auther MaxLiu
 * @time 2016/12/17
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        Repository.init(getFilesDir());
    }
}
