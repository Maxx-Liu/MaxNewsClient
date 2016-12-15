package com.max.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.R;
import com.max.news.adapter.HomePagerAdapter;
import com.max.news.adapter.HomeTabRecyclerAdapter;
import com.max.news.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页Fragment
 *
 * @author MaxLiu
 * @see MainActivity
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recycler_view_home)
    RecyclerView mRecyclerViewHome;
    @BindView(R.id.viewpager_home)
    ViewPager mViewpagerHome;
    @BindView(R.id.tab_layout_home)
    TabLayout mTabLayoutHome;

    private HomeTabRecyclerAdapter mRecyclerAdapter;
    private HomePagerAdapter mPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    /**
     * Init Home ViewPager
     */
    private void inigViewPager() {
        mPagerAdapter = new HomePagerAdapter(getActivity());
        mViewpagerHome.setAdapter(mPagerAdapter);
    }



    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
