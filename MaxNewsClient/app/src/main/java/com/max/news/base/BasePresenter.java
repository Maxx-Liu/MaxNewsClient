package com.max.news.base;

/**
 * @auther MaxLiu
 * @time 2016/12/26
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
