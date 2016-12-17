package com.max.news;

import android.app.Application;

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
    }
}
