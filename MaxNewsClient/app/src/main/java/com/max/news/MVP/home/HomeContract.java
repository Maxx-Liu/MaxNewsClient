package com.max.news.MVP.home;

import com.max.news.MVP.home.channelist.bean.ChannelListResBody;
import com.max.news.MVP.home.channelist.bean.ChannelTitle;
import com.max.news.base.BasePresenter;
import com.max.news.base.BaseView;
import com.max.news.db.cache.Repository;
import com.max.news.db.http.HttpResult;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 * Home底部菜单的合同.管理View接口和Presenter接口
 *
 * @auther MaxLiu
 * @time 2017/1/4
 */

public interface HomeContract {

    interface View extends BaseView<Presenter>{
        void loadViewPager(List<ChannelTitle> mChannelLists);
    }

    interface Presenter extends BasePresenter{
        void requestData();
    }

    interface Model {
        Observable<ChannelListResBody> getChannels();
    }
}
