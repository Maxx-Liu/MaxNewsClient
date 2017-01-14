package com.max.news.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.max.news.utils.AppManager;
import com.max.news.widget.BaseDialogFragment;
import com.max.news.widget.DialogFactory;

import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

/**
 * The BaseActivity
 *
 * For the majority of the Activity base class, part of the Activity to achieve a common part
 * of the code to prevent redundancy.
 *
 * @author MaxLiu
 * @since MaxNews-1.0.0
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public static final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    protected DialogFactory mDialogFactory;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    public abstract void initlayout();
    public abstract void widgetClick(View v);

    public BaseDialogFragment.BaseDialogListener getDialogListener(){
        return mDialogFactory.mListenerHolder.getDialogListener();
    }

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * 清空DialogListenerHolder中的dialog listener
     */
    public void clearDialogListener(){
        mDialogFactory.mListenerHolder.setDialogListener(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDialogFactory.mListenerHolder.saveDialogListenerKey(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogFactory = new DialogFactory(getSupportFragmentManager(),savedInstanceState);
        mDialogFactory.restoreDialogListener(this);
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        AppManager.getAppManager().addActivity(this);
        initlayout();
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }

    public static PublishSubject<ActivityLifeCycleEvent> getLifrCycle(){
        return lifecycleSubject;
    }
}
