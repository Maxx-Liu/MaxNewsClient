package com.max.news.home.channelist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.max.news.R;
import com.max.news.base.BaseFragment;
import com.max.news.home.channelist.adapter.HomeTabRecyclerAdapter;
import com.max.news.home.channelist.adapter.viewholder.HomeTabItemDecoration;
import com.max.news.home.channelist.pojo.ChannelInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomeTabFragment extends BaseFragment implements TabPagerContract.View , BaseRefreshListener{
    private static final String PARAMS_ID = "tab_id";
    private static final String PARAMS_TITLE = "tab_title";
    @BindView(R.id.recycler_view_tab)
    RecyclerView mRecyclerViewTab;
    @BindView(R.id.pull_refresh)
    PullToRefreshLayout mPullRefresh;

    private Context mContext;
    private int mPage = 1;
    private HomeTabRecyclerAdapter mHomeTabAdapter;
    private TabPagerContract.Presenter mPresenter;
    private static ArrayMap<String, HomeTabRecyclerAdapter> mAdapters = new ArrayMap<>();

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
        mPullRefresh.setRefreshListener(this);
        //设置刷新风格
        return mView;
    }

    /**
     * Init home RecyclerView
     */
    @Override
    public void loadRecyclerView(ChannelInfoBean.Pagebean pagebean) {
        HomeTabItemDecoration mDecoration = new HomeTabItemDecoration(
                mContext, HomeTabItemDecoration.HORIZONTAL_LIST);
        mRecyclerViewTab.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.VERTICAL,
                        false));
        if (mAdapters.get(getArguments().getString(PARAMS_ID)) == null) {
            HomeTabRecyclerAdapter mHomeTabAdapter = new HomeTabRecyclerAdapter(
                    getParentFragment().getActivity());
            mHomeTabAdapter.addPagebean(pagebean);
            mAdapters.put(getArguments().getString(PARAMS_ID), mHomeTabAdapter);
            mRecyclerViewTab.setAdapter(mHomeTabAdapter);
        } else {
            HomeTabRecyclerAdapter mHomeTabAdapter =
                    mAdapters.get(getArguments().getString(PARAMS_ID));
            mHomeTabAdapter.addPagebean(pagebean);
        }
        mRecyclerViewTab.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));

        mPullRefresh.finishRefresh();
        mPullRefresh.finishLoadMore();
    }

    @Override
    public void setPresenter(TabPagerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPage = 1;
        mPresenter.start();
        mPresenter.requestData(getArguments().getString(PARAMS_ID),
                getArguments().getString(PARAMS_TITLE),mPage);
    }


    @Override
    public void onStop() {
        super.onStop();
        mAdapters.remove(getArguments().getString(PARAMS_ID));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.start();
        mPresenter.requestData(getArguments().getString(PARAMS_ID),
                getArguments().getString(PARAMS_TITLE),mPage);
    }

    @Override
    public void loadMore() {
        mPage++;
        mPresenter.start();
        mPresenter.requestData(getArguments().getString(PARAMS_ID),
                getArguments().getString(PARAMS_TITLE),mPage);
    }
}
