package com.max.news.MVP.near;

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

public class NearFragment extends BaseFragment {

    public static NearFragment newInstance() {

        Bundle args = new Bundle();

        NearFragment fragment = new NearFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near,container,false);
        ButterKnife.bind(this,view);
        ButterKnife.setDebug(true);
        return view;
    }
}
