package com.max.news.application;

import android.app.Application;

/**
 * @auther MaxLiu
 * @time 2017/1/17
 */

public class NewsApplication extends Application {

    /**
     * 应用实例
     **/
    private static NewsApplication instance;

    /**
     * 获得实例
     *
     * @return
     */
    public static NewsApplication getInstance() {
        return instance;
    }
}
