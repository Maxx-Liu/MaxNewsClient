package com.max.news.MVP.home;

import dagger.Component;

/**
 * @auther MaxLiu
 * @time 2017/1/17
 */

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
