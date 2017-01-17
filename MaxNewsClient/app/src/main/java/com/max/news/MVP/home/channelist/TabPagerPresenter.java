package com.max.news.MVP.home.channelist;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.max.news.base.ActivityLifeCycleEvent;
import com.max.news.base.BaseActivity;
import com.max.news.MVP.home.channelist.pojo.ChannelInfoBean;
import com.max.news.db.http.ApiDefault;
import com.max.news.db.http.ApiException;
import com.max.news.db.http.HttpResult;
import com.max.news.db.http.HttpUtil;

import rx.Observable;
import rx.Observer;

/**
 * Home页面各种Tab的切换管理.
 *
 * @auther MaxLiu
 * @time 2016/12/26
 */

public class TabPagerPresenter implements TabPagerContract.Presenter{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabPagerContract.View IView;

    public TabPagerPresenter(TabPagerContract.View iView,
                             TabLayout tabLayout,
                             ViewPager viewPager) {
        IView = iView;
        IView.setPresenter(this);
        mTabLayout = tabLayout;
        mViewPager = viewPager;
    }

    @Override
    public void start() {

    }

    @Override
    public void requestData(String tabId,String tabTitle,int page) {
        String mCacheKey = tabId + "_list_" + tabTitle;
        //创建被观察者，传入数据
        Observable<HttpResult<ChannelInfoBean>> mObservable =
                ApiDefault.getApiDefault().getChannelInfo(tabId, tabTitle, "",
                        String.valueOf(page), "1", "0", "0", "20");
        //创建观察者
        Observer<ChannelInfoBean> mObserver = new Observer<ChannelInfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String mError;
                if(e instanceof ApiException){
                    mError = e.getMessage();
                }else {
                    mError = "请求失败，请稍后再试";
                }
            }

            @Override
            public void onNext(ChannelInfoBean channelInfo) {
                channelInfo.getRet_code();
                IView.loadRecyclerView(channelInfo.getPagebean());
            }
        };
        HttpUtil.getInstance().toSubscribe(
                mObservable,
                mObserver,
                mCacheKey,
                ActivityLifeCycleEvent.DESTROY,
                BaseActivity.getLifrCycle(),
                false,
                false);
    }
}
