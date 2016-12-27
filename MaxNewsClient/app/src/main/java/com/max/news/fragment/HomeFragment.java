package com.max.news.fragment;

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

import com.max.news.R;
import com.max.news.base.BaseFragment;
import com.max.news.http.ApiDefault;
import com.max.news.http.ApiException;
import com.max.news.http.HttpResult;
import com.max.news.http.HttpUtil;
import com.max.news.pojo.ChannelListResBody;
import com.max.news.pojo.ChannelTitle;
import com.max.news.presenter.TabPagerPresenter;
import com.max.news.ui.ActivityLifeCycleEvent;
import com.max.news.ui.MainActivity;
import com.max.news.ui.adapter.HomePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;

/**
 * 首页Fragment
 *
 * @author MaxLiu
 * @see MainActivity
 */

public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.viewpager_home)
    ViewPager mViewpagerHome;
    @BindView(R.id.tab_layout_home)
    TabLayout mTabLayoutHome;
    private static PublishSubject<ActivityLifeCycleEvent> lifecycleSubject;

    private HomePagerAdapter mPagerAdapter;
    private List<ChannelTitle> mChannelLists;
    private String mId;
    private String mTitle;
    private ArrayList<BaseFragment> fragments = new ArrayList<>();

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
        requestNetWorkData();
    }

    /**
     * Init Home ViewPager
     */
    private void inigViewPager(List<ChannelTitle> list) {
        mViewpagerHome.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return HomeTabFragment.newInstance(
                        mChannelLists.get(position).getChannelId(),
                        mChannelLists.get(position).getName());
            }

            @Override
            public int getCount() {
                return mChannelLists.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mChannelLists.get(position).getName();
            }
        });

        mTabLayoutHome.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayoutHome.setupWithViewPager(mViewpagerHome, true);
        new TabPagerPresenter(mTabLayoutHome, mViewpagerHome).bindHasData();
    }

    /**
     * 请求网络数据
     */
    private void requestNetWorkData() {
        Log.d("Request Success", "requestNetWorkData: success");
        Observable<HttpResult<ChannelListResBody>> mObservable =
                ApiDefault.getApiDefault().getChannelList();
        Observer<ChannelListResBody> mObserver =
                new Observer<ChannelListResBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String mError;
                        if (e instanceof ApiException) {
                            mError = e.getMessage();
                            Log.e(TAG, "onError: " + mError);
                        } else {
                            mError = "请求失败，请稍后再试";
                            Log.e(TAG, "onError: " + e.toString());
                        }
                    }

                    @Override
                    public void onNext(ChannelListResBody httpResult) {
                        Log.d("Request Success", httpResult.toString());
                        mChannelLists = httpResult.getChannelTitles();
                        for (int i = 0; i < 5; i++) {
                            HomeTabFragment mHomeTabFragment =
                                    HomeTabFragment.newInstance(
                                            mChannelLists.get(i).getChannelId(),
                                            mChannelLists.get(i).getName());
                            fragments.add(mHomeTabFragment);
                        }
                        inigViewPager(mChannelLists);
                    }
                };
        HttpUtil.getInstance()
                .toSubscribe(mObservable, mObserver,
                        "Titles",
                        ActivityLifeCycleEvent.DESTROY,
                        lifecycleSubject,
                        false, false);
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

}