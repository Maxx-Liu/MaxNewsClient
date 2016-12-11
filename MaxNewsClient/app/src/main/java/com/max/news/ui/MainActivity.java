package com.max.news.ui;
/**
 *  BottomBar属性一览：
 *  bb_tabXmlResource：
 *  设置标签的 xml 资源标识，在 res/xml/ 目录下。
 *  bb_tabletMode：
 *  是否是平板模式。true：是；false：不是。
 *  bb_behavior：（三种模式）
 *  shifting: 选定的标签比其他的更宽。
 *  shy: 将 Bottombar 放在 Coordinatorlayout 它会自动隐藏在滚动！（需要特定的布局）
 *  underNavbar: 正常模式（默认）。
 *  bb_inActiveTabAlpha：
 *  没选中时标签的透明度，icon 和 titiles 有用。（取值：0-1）
 *  bb_activeTabAlpha：
 *  选中时标签的透明度，与上一个相对应。（取值：0-1）
 *  bb_inActiveTabColor：
 *  没选时标签的颜色，icon 和 titiles 有用。
 *  bb_activeTabColor：
 *  选中时标签的颜色，与一个相对应。
 *  bb_badgeBackgroundColor：
 *  设置 Badges 的背景色，就是右上角显示数字那个。
 *  bb_titleTextAppearance：
 *  利用 style 重新设置自定的格式，例如：大小、加粗等。
 *  bb_titleTypeFace：
 *  设置自定的字体, 例： app:bb_titleTypeFace="fonts/MySuperDuperFont.ttf"。
 *  将字体放在 src/main/assets/fonts/MySuperDuperFont.ttf 路径下，
 *  只需要写 fonts/MySuperDuperFont.ttf 即可，将自动填充。
 *  bb_showShadow：
 *  控制阴影是否显示或隐藏，类似于蒙板，默认为true。
 */

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;

import com.max.news.R;
import com.max.news.net.RetrofitUntil;
import com.max.news.pojo.ChannelListBean;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements OnTabSelectListener,OnTabReselectListener{

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
        mBottomBar.setOnTabSelectListener(this);
        mBottomBar.setOnTabReselectListener(this);

        FragmentManager fragmentManager = new FragmentManager() {
            @Override
            public FragmentTransaction beginTransaction() {
                return null;
            }

            @Override
            public boolean executePendingTransactions() {
                return false;
            }

            @Override
            public Fragment findFragmentById(@IdRes int id) {
                return null;
            }

            @Override
            public Fragment findFragmentByTag(String tag) {
                return null;
            }

            @Override
            public void popBackStack() {

            }

            @Override
            public boolean popBackStackImmediate() {
                return false;
            }

            @Override
            public void popBackStack(String name, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(String name, int flags) {
                return false;
            }

            @Override
            public void popBackStack(int id, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(int id, int flags) {
                return false;
            }

            @Override
            public int getBackStackEntryCount() {
                return 0;
            }

            @Override
            public BackStackEntry getBackStackEntryAt(int index) {
                return null;
            }

            @Override
            public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {

            }

            @Override
            public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {

            }

            @Override
            public void putFragment(Bundle bundle, String key, Fragment fragment) {

            }

            @Override
            public Fragment getFragment(Bundle bundle, String key) {
                return null;
            }

            @Override
            public List<Fragment> getFragments() {
                return null;
            }

            @Override
            public Fragment.SavedState saveFragmentInstanceState(Fragment f) {
                return null;
            }

            @Override
            public boolean isDestroyed() {
                return false;
            }

            @Override
            public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {

            }
        };
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

    @Override
    public void onTabReSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                nearby.removeBadge();
                break;
            case R.id.tab_near:

                break;
            case R.id.tab_personal:

                break;
        }
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                // 选择指定 id 的标签
                nearby = mBottomBar.getTabWithId(R.id.tab_home);
                //设置Navigation信息数量
                //nearby.setBadgeCount(99);
                break;
            case R.id.tab_near:

                break;
            case R.id.tab_personal:

                break;
        }
    }
}
