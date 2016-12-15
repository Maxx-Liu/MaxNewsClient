package com.max.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.R;
import com.max.news.adapter.HomeTabRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomeTabFragment extends BaseFragment {
    private static final String PARAMS_TITLE = "title";
    @BindView(R.id.recycler_view_tab)
    RecyclerView mRecyclerViewTab;
    private HomeTabRecyclerAdapter mHomeTabAdapter;

    public static HomeTabFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(PARAMS_TITLE, title);
        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home_tab, container, false);
        ButterKnife.bind(this, mView);
        initRecyclerView();
        return mView;
    }

    /**
     * Init home RecyclerView
     */
    private void initRecyclerView() {
        mHomeTabAdapter = new HomeTabRecyclerAdapter(getActivity());
        mRecyclerViewTab.setLayoutManager(
                new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.VERTICAL,
                        false));
        mRecyclerViewTab.setAdapter(mHomeTabAdapter);
        requestNetWorkData();
    }

    private void requestNetWorkData() {
        String tabTitle = getArguments().getString(PARAMS_TITLE);
        //TODO 根据传入的频道标题请求网络数据
    }
}
