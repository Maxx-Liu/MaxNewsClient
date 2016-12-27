package com.max.news.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.max.news.widget.BaseDialogFragment;
import com.max.news.widget.DialogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 */
public abstract class BaseFragment extends Fragment {
    //时间格式
    private static final String TIME_STYLE = "yyyyMMddHHmmss";

    protected BaseActivity mBaseActivity;

    protected DialogFactory mDialogFactory ;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialogFactory = new DialogFactory(getChildFragmentManager(),savedInstanceState);
        mDialogFactory.restoreDialogListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            mBaseActivity = (BaseActivity)context;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 获取客户端当前时间
     * @return 格式 yyyyMMddHHmmss
     */
    public static String getCurrentTime() {
        Date mData = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                TIME_STYLE, Locale.getDefault());
        Log.d("CurrentTime", "Time : " + dateFormat.format(mData));
        return dateFormat.format(mData);
    }
}
