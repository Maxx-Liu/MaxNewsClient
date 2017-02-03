package com.max.news.base;

/**
 * Presenter基类，用于实现基础的Presenter的操作.
 *
 * @auther MaxLiu
 * @time 2016/12/26
 */

public interface BasePresenter {

    /**
     * 数据加载的初始化
     */
    void start();

    void detach();
}
