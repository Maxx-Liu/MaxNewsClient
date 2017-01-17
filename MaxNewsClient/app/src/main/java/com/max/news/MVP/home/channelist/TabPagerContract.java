package com.max.news.MVP.home.channelist;

import com.max.news.base.BasePresenter;
import com.max.news.base.BaseView;
import com.max.news.MVP.home.channelist.pojo.ChannelInfoBean;

/**
 * @auther MaxLiu
 * @time 2017/1/4
 */

public interface TabPagerContract {

    interface View extends BaseView<Presenter>{

        /**
         * 加载RecyclerView
         * @param pagebean 数据Bean
         */
        void loadRecyclerView(ChannelInfoBean.Pagebean pagebean);
    }

    interface Presenter extends BasePresenter{

        /**
         * 请求数据
         */
        void requestData(String tabId,String tabTitle,int page);
    }
}
