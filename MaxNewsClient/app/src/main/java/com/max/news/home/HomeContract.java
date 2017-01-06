package com.max.news.home;

import com.max.news.base.BasePresenter;
import com.max.news.base.BaseView;
import com.max.news.home.channelist.pojo.ChannelTitle;

import java.util.List;

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
}
