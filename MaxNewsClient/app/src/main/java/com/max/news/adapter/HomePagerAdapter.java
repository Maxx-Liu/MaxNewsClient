package com.max.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.max.news.fragment.BaseFragment;
import com.max.news.pojo.ChannelTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private List<ChannelTitle> mChannelLists;

    public HomePagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments,
                            List<ChannelTitle> channelLists){
        super(fm);
        mFragments = fragments;
        mChannelLists = channelLists;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannelLists.get(position).getName();
    }
}
