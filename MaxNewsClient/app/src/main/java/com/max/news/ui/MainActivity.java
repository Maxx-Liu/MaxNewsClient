package com.max.news.ui;
/**
 * BottomBar属性一览：
 * bb_tabXmlResource：
 * 设置标签的 xml 资源标识，在 res/xml/ 目录下。
 * bb_tabletMode：
 * 是否是平板模式。true：是；false：不是。
 * bb_behavior：（三种模式）
 * shifting: 选定的标签比其他的更宽。
 * shy: 将 Bottombar 放在 Coordinatorlayout 它会自动隐藏在滚动！（需要特定的布局）
 * underNavbar: 正常模式（默认）。
 * bb_inActiveTabAlpha：
 * 没选中时标签的透明度，icon 和 titiles 有用。（取值：0-1）
 * bb_activeTabAlpha：
 * 选中时标签的透明度，与上一个相对应。（取值：0-1）
 * bb_inActiveTabColor：
 * 没选时标签的颜色，icon 和 titiles 有用。
 * bb_activeTabColor：
 * 选中时标签的颜色，与一个相对应。
 * bb_badgeBackgroundColor：
 * 设置 Badges 的背景色，就是右上角显示数字那个。
 * bb_titleTextAppearance：
 * 利用 style 重新设置自定的格式，例如：大小、加粗等。
 * bb_titleTypeFace：
 * 设置自定的字体, 例： app:bb_titleTypeFace="fonts/MySuperDuperFont.ttf"。
 * 将字体放在 src/main/assets/fonts/MySuperDuperFont.ttf 路径下，
 * 只需要写 fonts/MySuperDuperFont.ttf 即可，将自动填充。
 * bb_showShadow：
 * 控制阴影是否显示或隐藏，类似于蒙板，默认为true。
 */

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.max.news.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener,OnTabSelectListener, OnTabReselectListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.fragment_main)
    FrameLayout mFragmentMain;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;
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

        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigation
                .addItem(new BottomNavigationItem(R.mipmap.bottom_navigation_home,
                        getString(R.string.bottom_navigation_home))
                        .setInactiveIconResource(R.mipmap.bottom_navigation_home)
                        .setActiveColorResource(R.color.colorPrimary)
                        .setInActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_navigation_near,
                        getString(R.string.bottom_navigation_near))
                        .setInactiveIconResource(R.mipmap.bottom_navigation_near)
                        .setActiveColorResource(R.color.colorPrimary)
                        .setInActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_navigation_personal,
                        getString(R.string.bottom_navigation_personal))
                        .setInactiveIconResource(R.mipmap.bottom_navigation_personal)
                        .setActiveColorResource(R.color.colorPrimary)
                        .setInActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigation.setTabSelectedListener(this);

    }

    private void requestNet() {
        //NetWorkUntil.requestNetWork();
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

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
