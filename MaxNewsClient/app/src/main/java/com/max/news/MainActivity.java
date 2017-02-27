package com.max.news;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.max.news.MVP.home.HomeFragment;
import com.max.news.MVP.home.HomePresenter;
import com.max.news.MVP.near.NearFragment;
import com.max.news.MVP.personal.MyFragment;
import com.max.news.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MainActivity,此app的启动活动
 * <p>
 * 实现了底部导航,可复用,换掉添加的Fragment或者直接修改HomeFragment{@link HomeFragment}
 * 等对应的Fragment即可.
 *
 * @author MaxLiu
 * @see HomeFragment
 */

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MainActivity";
    // the fragment position
    private static final int TAB_SHOW_HOME_ID = 0;
    private static final int TAB_SHOW_NEAR_ID = 1;
    private static final int TAB_SHOW_PERSONAL_ID = 2;
    @BindView(R.id.fragment_main)
    FrameLayout mFragmentMain;
    @BindView(R.id.bottom_navigation)
    BottomNavigationBar mBottomNavigation;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    //private BottomBarTab nearby;
    //private BadgeItem badge;
    private HomeFragment mHomeFragment;
    private NearFragment mNearFragment;
    private MyFragment mMyFragment;

    @Override
    public void initLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void widgetClick(View v) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    private void initBadgeItem(String num) {
//        badge = new BadgeItem()
//                .setBorderWidth(2)//Badge的Border(边界)宽度
//                .setBorderColor("#FF0000")//Badge的Border颜色
//                .setBackgroundColor("#9ACD32")//Badge背景颜色
//                .setGravity(Gravity.RIGHT| Gravity.TOP)//位置，默认右上角
//                .setText(num);//显示的文本
//                .setTextColor("#F0F8FF")//文本颜色
//                .setAnimationDuration(2000)
//                .setHideOnSelect(true)//当选中状态时消失，非选中状态显示
//    }

    private void initView() {
        // 设置标题样式
        toolBar.setTitle(getString(R.string.app_name));
        toolBar.setTitleTextColor(Color.WHITE);
        // 设置支持 ActionBar
        setSupportActionBar(toolBar);
        // 实现抽屉功能
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolBar,
                R.string.open,
                R.string.close
        );
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
//        mBottomBar.setOnTabSelectListener(this);
//        mBottomBar.setOnTabReselectListener(this);

        /* MODE_DEFAULT:如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         * MODE_FIXED:填充模式，未选中的Item会显示文字，没有换挡动画。
         * MODE_SHIFTING:换挡模式，未选中的Item不会显示文字，选中的会显示文字，
         *               在切换的时候会有一个像换挡的动画。
         */
        mBottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);
        /* BACKGROUND_STYLE_DEFAULT:如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。
                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
           BACKGROUND_STYLE_STATIC:点击的时候没有水波纹效果
           BACKGROUND_STYLE_RIPPLE:点击的时候有水波纹效果
         */
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        //是否自动隐藏
        mBottomNavigation.setAutoHideEnabled(true);
        mBottomNavigation
                .addItem(new BottomNavigationItem(R.mipmap.bottom_navigation_home,
                        getString(R.string.bottom_navigation_home))
                        .setInactiveIconResource(R.mipmap.bottom_navigation_home)
                        /*
                        在BACKGROUND_STYLE_STATIC 下，表示选中Item的图标和文本颜色。而在
                        BACKGROUND_STYLE_RIPPLE 下，表示整个容器的背景色。默认Theme's Primary Color
                         */
                        //.setActiveColorResource()
                        /*
                        在BACKGROUND_STYLE_STATIC下，表示整个容器的背景色。而在
                        BACKGROUND_STYLE_RIPPLE下，表示选中Item的图标和文本颜色。默认 Color.WHITE
                         */
                        //.setBarBackgroundColor();
                        //表示未选中Item中的图标和文本颜色。默认为 Color.LTGRAY
                        .setInActiveColor(Color.WHITE))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_navigation_near,
                        getString(R.string.bottom_navigation_near))
                        .setInActiveColor(Color.WHITE))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_navigation_personal,
                        getString(R.string.bottom_navigation_personal))
                        .setInActiveColor(Color.WHITE))
                .setFirstSelectedPosition(TAB_SHOW_HOME_ID)
                .initialise();
        mBottomNavigation.setTabSelectedListener(this);
        FragmentTransaction beginTransaction =
                getSupportFragmentManager().beginTransaction();
        mHomeFragment = HomeFragment.newInstance();
        beginTransaction.replace(R.id.fragment_main, mHomeFragment);
        beginTransaction.commit();
        new HomePresenter(mHomeFragment);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        FragmentTransaction beginTransaction =
                getSupportFragmentManager().beginTransaction();
        hideFragment(beginTransaction);
        switch (tabId) {
            case TAB_SHOW_HOME_ID:
                Log.d(TAG, "onTabSelected: " + TAB_SHOW_HOME_ID);
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    beginTransaction.add(R.id.fragment_main, mHomeFragment);
                } else {
                    beginTransaction.show(mHomeFragment);
                }
                break;
            case TAB_SHOW_NEAR_ID:
                if (mNearFragment == null) {
                    mNearFragment = NearFragment.newInstance();
                    beginTransaction.add(R.id.fragment_main, mNearFragment);
                } else {
                    beginTransaction.show(mNearFragment);
                }
                break;
            case TAB_SHOW_PERSONAL_ID:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance();
                    beginTransaction.add(R.id.fragment_main, mMyFragment);
                } else {
                    beginTransaction.show(mMyFragment);
                }
                break;
        }
        beginTransaction.commit();
    }

    /**
     * Hide LauncherRFragment or Launcher2RFragment
     *
     * @param ft FragmentTransaction
     */
    public void hideFragment(FragmentTransaction ft) {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }

        if (mNearFragment != null) {
            ft.hide(mNearFragment);
        }

        if (mMyFragment != null) {
            ft.hide(mMyFragment);
        }
    }

    @Override
    public void onTabUnselected(int position) {
        switch (position) {
            case 0:

                break;
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
