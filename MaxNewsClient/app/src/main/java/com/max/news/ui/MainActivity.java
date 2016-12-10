package com.max.news.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.widget.FrameLayout;

import com.max.news.R;
import com.max.news.net.RetrofitUntil;
import com.max.news.pojo.ChannelListBean;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.fragment_main)
    FrameLayout mFragmentMain;
    private BottomBarTab nearby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        requestNet();
        initView();
    }

    private void initView() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.bottom_bar) {
                    // 选择指定 id 的标签
                    nearby = mBottomBar.getTabWithId(R.id.tab_home);
                    nearby.setBadgeCount(2);
                }
            }
        });
        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_near) {
                    // 已经选择了这个标签，又点击一次。即重选。
                    nearby.removeBadge();
                }
            }
        });
    }

    private void requestNet() {
        RetrofitUntil.getChannelListCall().enqueue(new Callback<ChannelListBean>() {

            @Override
            public void onResponse(Call<ChannelListBean> call, Response<ChannelListBean> response) {
                ChannelListBean mChannelListBean = response.body();
                Log.d(TAG, "JSON : " + "Error: " + mChannelListBean.getShowapiResError()
                        + "Code: " + mChannelListBean.getShowapiResCode()
                        + "\n JSON: " + mChannelListBean.getChannelListResBody().getChannelList().get(1).getName());
            }

            @Override
            public void onFailure(Call<ChannelListBean> call, Throwable t) {

            }
        });
    }

}
