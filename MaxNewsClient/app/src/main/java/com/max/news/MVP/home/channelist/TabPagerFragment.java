package com.max.news.MVP.home.channelist;

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
import com.max.news.MVP.home.channelist.adapter.HomeTabRecyclerAdapter;
import com.max.news.MVP.home.channelist.adapter.viewholder.HomeTabItemDecoration;
import com.max.news.MVP.home.channelist.bean.ChannelInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class TabPagerFragment extends BaseFragment implements
        TabPagerContract.View , BaseRefreshListener,HomeTabRecyclerAdapter.ItemOnClickListener{
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

    public static TabPagerFragment newInstance(String id, String title) {
        Bundle args = new Bundle();
        args.putString(PARAMS_ID, id);
        args.putString(PARAMS_TITLE, title);
        TabPagerFragment fragment = new TabPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home_tab, container, false);
        ButterKnife.bind(this, mView);
        mPullRefresh.setRefreshListener(this);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Init home RecyclerView
     */
    @Override
    public void loadRecyclerView(ChannelInfoBean.Pagebean pagebean) {
//        HomeTabItemDecoration mDecoration = new HomeTabItemDecoration(
//                mContext, HomeTabItemDecoration.HORIZONTAL_LIST);
        if (mHomeTabAdapter == null) {
            mRecyclerViewTab.setLayoutManager(
                    new LinearLayoutManager(
                            getParentFragment().getActivity(),
                            LinearLayoutManager.VERTICAL,
                            false));
            mHomeTabAdapter = new HomeTabRecyclerAdapter(
                    getParentFragment().getActivity(),this);
            mRecyclerViewTab.setAdapter(mHomeTabAdapter);
            mRecyclerViewTab.addItemDecoration(
                    new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        }
        mHomeTabAdapter.addPagebean(pagebean);
        mPullRefresh.finishRefresh();
        mPullRefresh.finishLoadMore();

    }

    @Override
    public void setPresenter(TabPagerContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapters.remove(getArguments().getString(PARAMS_ID));
        mPullRefresh.finishRefresh();
        mPullRefresh.finishLoadMore();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void refresh() {
        mPage = 1;
        mPresenter.requestData(getArguments().getString(PARAMS_ID),
                getArguments().getString(PARAMS_TITLE),mPage);
    }

    @Override
    public void loadMore() {
        mPage++;
        mPresenter.requestData(getArguments().getString(PARAMS_ID),
                getArguments().getString(PARAMS_TITLE),mPage);
    }

    @Override
    public void OnClick(ChannelInfoBean.Pagebean.ContentlistBean mContentlistBean) {

    }
}
