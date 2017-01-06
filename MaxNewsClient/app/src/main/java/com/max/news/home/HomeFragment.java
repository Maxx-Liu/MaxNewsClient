package com.max.news.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.MainActivity;
import com.max.news.R;
import com.max.news.base.ActivityLifeCycleEvent;
import com.max.news.base.BaseFragment;
import com.max.news.home.channelist.HomeTabFragment;
import com.max.news.home.channelist.TabPagerPresenter;
import com.max.news.home.channelist.pojo.ChannelTitle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

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
    private static PublishSubject<ActivityLifeCycleEvent> lifecycleSubject;
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
        mViewpagerHome.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                HomeTabFragment homeTabFragment =HomeTabFragment.newInstance(
                        channelLists.get(position).getChannelId(),
                        channelLists.get(position).getName());
                new TabPagerPresenter(homeTabFragment,mTabLayoutHome,mViewpagerHome);
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

        mTabLayoutHome.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayoutHome.setupWithViewPager(mViewpagerHome, true);
        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        mViewpagerHome.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutHome));

        mTabLayoutHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("xyz", "pos " + mTabLayoutHome.getSelectedTabPosition());
                mViewpagerHome.setCurrentItem(mTabLayoutHome.getSelectedTabPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static HomeFragment newInstance(
            PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        HomeFragment.lifecycleSubject = lifecycleSubject;
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
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }
}