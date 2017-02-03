package com.max.news.MVP.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.MainActivity;
import com.max.news.R;
import com.max.news.base.BaseApplication;
import com.max.news.base.BaseFragment;
import com.max.news.MVP.home.channelist.HomeTabFragment;
import com.max.news.MVP.home.channelist.TabPagerPresenter;
import com.max.news.MVP.home.channelist.bean.ChannelTitle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页Fragment
 *
 * @author MaxLiu
 * @see MainActivity
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.viewpager_home)
    ViewPager mViewpagerHome;
    @BindView(R.id.tab_layout_home)
    TabLayout mTabLayoutHome;
    private HomeContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.requestData();
    }

    /**
     * Init Home ViewPager
     */
    @Override
    public void loadViewPager(final List<ChannelTitle> channelLists) {
        mTabLayoutHome.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayoutHome.setupWithViewPager(mViewpagerHome, true);
        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        mViewpagerHome.setOffscreenPageLimit(5);
        mViewpagerHome.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutHome));

        mTabLayoutHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpagerHome.setCurrentItem(mTabLayoutHome.getSelectedTabPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewpagerHome.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public Fragment getItem(int position) {
                HomeTabFragment homeTabFragment =HomeTabFragment.newInstance(
                        channelLists.get(position).getChannelId(),
                        channelLists.get(position).getName());
                new TabPagerPresenter(homeTabFragment);
                return homeTabFragment;
            }

            @Override
            public int getCount() {
                return channelLists.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return channelLists.get(position).getName();
            }
        });
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        Log.d("test", "A new HomeFragment");
        return fragment;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}