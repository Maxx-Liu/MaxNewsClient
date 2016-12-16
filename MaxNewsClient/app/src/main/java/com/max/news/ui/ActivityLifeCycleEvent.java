package com.max.news.ui;

/**
 * 枚举：Activity的几种状态
 * (后期改为RxLifecycle库)
 *
 * @auther MaxLiu
 * @time 2016/12/16
 */

public enum  ActivityLifeCycleEvent {
    CREATE,
    START,
    RESUME,
    PAUSE,
    STOP,
    DESTROY
}
