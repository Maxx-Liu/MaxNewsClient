package com.max.news.MVP.home.channelist;

import android.util.Log;

import com.max.news.MVP.home.channelist.bean.ChannelInfoBean;
import com.max.news.db.http.ApiException;
import com.max.news.db.http.Url;
import com.max.news.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;

/**
 * Home页面各种Tab的切换管理.
 *
 * @auther MaxLiu
 * @time 2016/12/26
 */

public class TabPagerPresenter implements TabPagerContract.Presenter {
    private TabPagerContract.View IView;
    private TabPagerContract.Model IModel;
    private Map<String, String> map = new HashMap<>();

    public TabPagerPresenter(TabPagerContract.View iView) {
        IView = iView;
        IView.setPresenter(this);
        IModel = new TabPagerModel();
    }

    @Override
    public void start() {
        map.put(Url.PARAM_CHANNEL_TITLE, "");
        map.put(Url.PARAM_CHANNEL_NEED_CONTENT, "0");
        map.put(Url.PARAM_CHANNEL_NEED_HTML, "0");
        map.put(Url.PARAM_CHANNEL_NEED_ALLLIST, "0");
        map.put(Url.PARAM_CHANNEL_NEED_RESULT, "20");
    }

    @Override
    public void detach() {
        map.clear();
    }

    @Override
    public void requestData(String tabId, String tabTitle, int page) {
        map.put(Url.PARAM_CHANNEL_NAME, tabTitle);
        map.put(Url.PARAM_CHANNEL_PAGE, String.valueOf(page));
        Log.e("Info", "page : " + String.valueOf(page));
        IModel.getChannelInfo(map,page)
                .subscribe(new Observer<ChannelInfoBean>() {
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
                        LogUtil.e("Tab", mError);
                        Log.e("Tab", "Failed!!!" + e.toString());
                    }

                    @Override
                    public void onNext(ChannelInfoBean channelInfo) {
                        channelInfo.getRet_code();
                        IView.loadRecyclerView(channelInfo.getPagebean());
                        Log.e("Tab", "SUCCESS!!!");
                    }
                });
    }
}
