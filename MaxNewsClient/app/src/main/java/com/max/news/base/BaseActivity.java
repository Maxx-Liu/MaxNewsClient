package com.max.news.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.max.news.ui.ActivityLifeCycleEvent;
import com.max.news.widget.BaseDialogFragment;
import com.max.news.widget.DialogFactory;

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

public class BaseActivity extends AppCompatActivity {

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

//    protected abstract void initButterKnife();
//    protected abstract int getLayout();
//    protected abstract void initEventAndData();

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

    public static PublishSubject<ActivityLifeCycleEvent> getLifrCycle(){
        return lifecycleSubject;
    }
}
