package com.max.news.MVP.home.channelist;

import android.util.Log;

import com.max.news.MVP.home.channelist.bean.ChannelInfoBean;
import com.max.news.db.cache.Repository;

import java.util.Map;

import rx.Observable;

/**
 * @auther MaxLiu
 * @time 2017/2/3
 */

class TabPagerModel implements TabPagerContract.Model {

    private Repository repository;

    TabPagerModel(){
        repository = new Repository();
    }

    @Override
    public Observable<ChannelInfoBean> getChannelInfo(Map<String, String> map,int page) {
        Log.e("info", map.get("title"));
        return repository.getChannelInfo(map,"test",page,false);
    }
}
