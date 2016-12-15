package com.max.news.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * @auther MaxLiu
 * @time 2016/12/15
 */

public class HomePagerAdapter extends PagerAdapter {
    private Context mContext;

    public HomePagerAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
