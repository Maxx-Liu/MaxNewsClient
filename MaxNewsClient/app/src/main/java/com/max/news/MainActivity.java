package com.max.news;
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

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.max.news.base.BaseActivity;
import com.max.news.home.HomeFragment;
import com.max.news.home.HomePresenter;

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
    //private BottomBarTab nearby;
    private BadgeItem badge;
    private HomeFragment mHomeFragment;

    @Override
    public void initlayout() {
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

    private void initBadgeItem(String num) {
        badge = new BadgeItem()
//                .setBorderWidth(2)//Badge的Border(边界)宽度
//                .setBorderColor("#FF0000")//Badge的Border颜色
//                .setBackgroundColor("#9ACD32")//Badge背景颜色
//                .setGravity(Gravity.RIGHT| Gravity.TOP)//位置，默认右上角
                .setText(num);//显示的文本
//                .setTextColor("#F0F8FF")//文本颜色
//                .setAnimationDuration(2000)
//                .setHideOnSelect(true)//当选中状态时消失，非选中状态显示
    }

    private void initView() {
//        mBottomBar.setOnTabSelectListener(this);
//        mBottomBar.setOnTabReselectListener(this);

        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
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
        mHomeFragment = HomeFragment.newInstance(lifecycleSubject);
        beginTransaction.replace(R.id.fragment_main, mHomeFragment);
        beginTransaction.commit();
        new HomePresenter(mHomeFragment);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        FragmentTransaction beginTransaction =
                getSupportFragmentManager().beginTransaction();
        switch (tabId) {
            case TAB_SHOW_HOME_ID:
                Log.d(TAG, "onTabSelected: " + TAB_SHOW_HOME_ID);
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance(lifecycleSubject);
                }
                beginTransaction.replace(R.id.fragment_main, mHomeFragment);
                break;
            case TAB_SHOW_NEAR_ID:

                break;
            case TAB_SHOW_PERSONAL_ID:

                break;
        }
        beginTransaction.commit();
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
