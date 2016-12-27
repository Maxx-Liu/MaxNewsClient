package com.max.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.R;
import com.max.news.base.BaseActivity;
import com.max.news.base.BaseFragment;
import com.max.news.http.ApiDefault;
import com.max.news.http.ApiException;
import com.max.news.http.HttpResult;
import com.max.news.http.HttpUtil;
import com.max.news.pojo.ChannelInfoBean;
import com.max.news.ui.ActivityLifeCycleEvent;
import com.max.news.ui.adapter.HomeTabRecyclerAdapter;
import com.max.news.ui.adapter.viewholder.HomeTabItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomeTabFragment extends BaseFragment {
    private static final String TAG = "HomeTabFragment";
    private static final String PARAMS_ID = "tab_id";
    private static final String PARAMS_TITLE = "tab_title";
    @BindView(R.id.recycler_view_tab)
    RecyclerView mRecyclerViewTab;
    private HomeTabRecyclerAdapter mHomeTabAdapter;
    private ChannelInfoBean.Pagebean mPagebean;
    private Context mContext;

    public static HomeTabFragment newInstance(String id, String title) {
        Bundle args = new Bundle();
        args.putString(PARAMS_ID, id);
        args.putString(PARAMS_TITLE, title);
        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home_tab, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        requestNetWorkData();
    }

    /**
     * Init home RecyclerView
     */
    private void initRecyclerView() {
        mHomeTabAdapter = new HomeTabRecyclerAdapter(
                getParentFragment().getActivity(),mPagebean);
        HomeTabItemDecoration mDecoration = new HomeTabItemDecoration(
                mContext,HomeTabItemDecoration.HORIZONTAL_LIST);
        mRecyclerViewTab.addItemDecoration(mDecoration);
        mRecyclerViewTab.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.VERTICAL,
                        false));
        mRecyclerViewTab.setAdapter(mHomeTabAdapter);
    }

    private void requestNetWorkData() {
        String tabTitle = getArguments().getString(PARAMS_TITLE);
        String tabId = getArguments().getString(PARAMS_ID);
        String mCacheKey = tabTitle + "_ChannelInfoList_" + tabId;
        //创建被观察者，传入数据
        Observable<HttpResult<ChannelInfoBean>> mObservable =
                ApiDefault.getApiDefault().getChannelInfo(tabId, tabTitle, "",
                        "1", "1", "0", "0", "20");
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
                    Log.e(TAG, "onError: " + mError);
                }else {
                    mError = "请求失败，请稍后再试";
                    Log.e(TAG, "onError: " + e.toString());
                }
            }

            @Override
            public void onNext(ChannelInfoBean channelInfo) {
                channelInfo.getRet_code();
                mPagebean = channelInfo.getPagebean();
                initRecyclerView();
                Log.d(TAG,"request success : " + channelInfo.toString());
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
