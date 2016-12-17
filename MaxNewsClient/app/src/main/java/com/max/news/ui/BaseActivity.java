package com.max.news.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.max.news.widget.BaseDialogFragment;
import com.max.news.widget.DialogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

public abstract class BaseActivity extends AppCompatActivity {
    //时间格式
    private static final String TIME_STYLE = "yyyyMMddHHmmss";

    public static final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    protected DialogFactory mDialogFactory;

    public BaseDialogFragment.BaseDialogListener getDialogListener(){
        return mDialogFactory.mListenerHolder.getDialogListener();
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

    /**
     * 获取客户端当前时间
     * @return 格式 yyyyMMddHHmmss
     */
    public String getCurrentTime() {
        Date mData = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                TIME_STYLE, Locale.getDefault());
        Log.d("CurrentTime", "Time : " + dateFormat.format(mData));
        return dateFormat.format(mData);
    }

    public static PublishSubject<ActivityLifeCycleEvent> getLifrCycle(){
        return lifecycleSubject;
    }
}
