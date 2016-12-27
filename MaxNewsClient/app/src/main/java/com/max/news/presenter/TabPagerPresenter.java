package com.max.news.presenter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * @auther MaxLiu
 * @time 2016/12/26
 */

public class TabPagerPresenter{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public TabPagerPresenter(TabLayout tabLayout, ViewPager viewPager) {
        mTabLayout = tabLayout;
        mViewPager = viewPager;
    }

    public void bindHasData() {
        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("xyz", "pos " + mTabLayout.getSelectedTabPosition());
                mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
