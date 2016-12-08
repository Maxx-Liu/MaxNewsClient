package com.max.news.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.max.news.widget.BaseDialogFragment;
import com.max.news.widget.DialogFactory;

/**
 * The BaseActivity
 *
 * For the majority of the Activity base class, part of the Activity to achieve a common part
 * of the code to prevent redundancy.
 *
 * @author MaxLiu
 * @since MaxNews-1.0.0
 */

public abstract class BaseActivity extends FragmentActivity {

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
