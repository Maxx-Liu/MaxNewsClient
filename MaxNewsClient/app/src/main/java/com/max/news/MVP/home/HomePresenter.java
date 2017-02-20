package com.max.news.MVP.home;

import com.max.news.MVP.home.channelist.bean.ChannelListResBody;
import com.max.news.MVP.home.channelist.bean.ChannelTitle;
import com.max.news.db.http.ApiException;
import com.max.news.utils.LogUtil;

import org.json.JSONObject;

import java.util.List;
import java.util.logging.Logger;

import rx.Observer;

/**
 * Home模块的Presenter,用于控制Home模块的业务逻辑
 *
 * @auther MaxLiu
 * @time 2017/1/4
 * @see HomeModel
 * @see HomeFragment
 * @see HomeContract
 */

public class HomePresenter implements HomeContract.Presenter {
    private static final String TAG = "HomePresenter";

    private HomeContract.View IView;
    private HomeContract.Model IModel;

    public HomePresenter(HomeContract.View iView) {
        IView = iView;
        IView.setPresenter(this);
        IModel = new HomeModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }

    @Override
    public void requestData() {
        IModel.getChannels().subscribe(
                new Observer<ChannelListResBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        String mError;
                        if (e instanceof ApiException) {
                            mError = e.getMessage();
                        } else {
                            mError = "请求失败，请稍后再试";
                        }
                        LogUtil.e(TAG,mError);
                    }

                    @Override
                    public void onNext(ChannelListResBody httpResult) {
                        List<ChannelTitle> mChannelLists = httpResult.getChannelTitles();
                        IView.loadViewPager(mChannelLists);
                    }
                }
        );

    }
}
