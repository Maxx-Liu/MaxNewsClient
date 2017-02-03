package com.max.news.MVP.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.news.R;
import com.max.news.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * @auther MaxLiu
 * @time 2017/2/3
 */

public class MyFragment extends BaseFragment{

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
