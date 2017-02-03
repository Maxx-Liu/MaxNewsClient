package com.max.news.MVP.home;

import com.max.news.MVP.home.channelist.bean.ChannelListResBody;
import com.max.news.db.cache.Repository;
import com.max.news.db.http.ApiService;
import com.max.news.db.http.HttpResult;
import com.max.news.db.http.RetrofitUtil;

import java.io.File;

import rx.Observable;

/**
 * @auther MaxLiu
 * @time 2017/1/18
 */

public class HomeModel implements HomeContract.Model{

    private Repository repository;
    public HomeModel(){
        repository = new Repository();
    }
    @Override
    public Observable<ChannelListResBody> getChannels() {
        return repository.getChannels(
                "test",
                false
        );
    }
}
