package com.max.news.home;

import dagger.Module;
import dagger.Provides;

/**
 * @auther MaxLiu
 * @time 2017/1/17
 */

@Module
public class HomeModule {
    private final HomeContract.View iView;

    public HomeModule(HomeContract.View iView) {
        this.iView = iView;
    }

    @Provides
    HomeContract.View provideHomeView() {
        return iView;
    }
}
