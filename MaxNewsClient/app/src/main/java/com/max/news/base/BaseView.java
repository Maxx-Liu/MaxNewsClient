package com.max.news.base;

/**
 * View的基类，用于实现View层的基础操作.
 *
 * @auther MaxLiu
 * @time 2016/12/26
 */

public interface BaseView<T> {

    /**
     * 在View中设置Presenter
     * @param presenter 传入的对应presenter实例
     */
    void setPresenter(T presenter);
}
