package com.max.news.home;

import com.max.news.base.ActivityLifeCycleEvent;
import com.max.news.home.channelist.pojo.ChannelListResBody;
import com.max.news.home.channelist.pojo.ChannelTitle;
import com.max.news.http.ApiDefault;
import com.max.news.http.ApiException;
import com.max.news.http.HttpResult;
import com.max.news.http.HttpUtil;

import java.util.List;

import rx.Observable;
import rx.Observer;

import static com.max.news.base.BaseActivity.lifecycleSubject;

/**
 * @auther MaxLiu
 * @time 2017/1/4
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View IView;

    public HomePresenter(HomeContract.View iView){
        IView = iView;
        iView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestData() {
        Observable<HttpResult<ChannelListResBody>> mObservable =
                ApiDefault.getApiDefault().getChannelList();
        Observer<ChannelListResBody> mObserver =
                new Observer<ChannelListResBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String mError;
                        if (e instanceof ApiException) {
                            mError = e.getMessage();
                        } else {
                            mError = "请求失败，请稍后再试";
                        }
                    }

                    @Override
                    public void onNext(ChannelListResBody httpResult) {
                        List<ChannelTitle> mChannelLists = httpResult.getChannelTitles();
                        IView.loadViewPager(mChannelLists);
                    }
                };
        HttpUtil.getInstance()
                .toSubscribe(mObservable, mObserver,
                        "ChannelTitleList",
                        ActivityLifeCycleEvent.DESTROY,
                        lifecycleSubject,
                        false, false);
    }
}
