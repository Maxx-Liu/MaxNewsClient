package com.max.news.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.max.news.ui.BaseActivity;
import com.max.news.fragment.BaseFragment;

/**
 * 所有自定义DialogFragment的基类，实现了基本的Argument数据传输
 *
 * @author Max Liu
 * @since MaxNews-1.0.0
 */

public class BaseDialogFragment extends DialogFragment {

    private static final String PARAMS_TITLE = "dialog_confirm_title";
    private static final String PARAMS_MESSAGE = "dialog_confirm_message";
    private static final String PARAMS_IS_CANCEL = "dialog_confirm_cancel";

    private BaseActivity mBaseActivity;
    //是否是自定义dialog
    protected boolean mIsCustomDialog = false;
    public String mTitle;
    public String mContent;
    public boolean mIsCancelable;

    public interface BaseDialogListener {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArgs(getArguments());
        setCancelable(mIsCancelable);

    }

    @Override
    public void onResume() {
        super.onResume();
        BaseDialogListener listener = null;

        if (!mIsCustomDialog) {
            mIsCustomDialog = true;
            /* 解析dialog listener，fragment的级别要大于activity，若 (getParentFragment() instanceof BaseFragment)为true
             * ，表明是一个fragment调起的dialog，否则是一个activity调起的diaolog
             * */
            if (getParentFragment() instanceof BaseFragment) {
                listener = ((BaseFragment) getParentFragment()).getDialogListener();
            } else if (mBaseActivity != null) {
                listener = mBaseActivity.getDialogListener();
            }
            if (listener != null) {
                onReceiveDialogListener(listener);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
         /* 销毁basefragment或BaseActivity中的dialog listener，
          * 同理BaseFragment级别要高于BaseActivity
          * */
        if (getParentFragment() instanceof BaseFragment) {
            ((BaseFragment) getParentFragment()).clearDialogListener();
        } else if (mBaseActivity != null) {
            mBaseActivity.clearDialogListener();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof BaseActivity) {
            mBaseActivity = (BaseActivity) getActivity();
        }
    }

    /**
     * 接受对话框的Listener对象
     *
     * @param listener dialog listener对象
     */
    protected void onReceiveDialogListener(BaseDialogListener listener) {

    }

    /**
     * 从bundle中解析参数，args有可能来自fragment被系统回收，然后界面又重新被启动系统保存的参数；也有可能是其他使用者带过来的
     * ，具体实现交给子类
     *
     * @param args argument
     */
    public void parseArgs(Bundle args) {
        mTitle = args.getString(PARAMS_TITLE);
        mContent = args.getString(PARAMS_MESSAGE);
        mIsCancelable = args.getBoolean(PARAMS_IS_CANCEL);
    }

    /**
     * 解析Arguments的Message数据
     *
     * @return String类型的message
     */
    public String parseMsg() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return null;
        }
        return bundle.getString(PARAMS_MESSAGE);
    }

    /**
     * 传入title数据
     *
     * @param bundle bundle对象
     * @param title  String类型的title
     */
    public static void putTitleParam(Bundle bundle, String title) {
        bundle.putString(PARAMS_TITLE, title);
    }

    /**
     * 传入message数据
     *
     * @param bundle  bundle对象
     * @param message String类型的message
     */
    public static void putMessageParam(Bundle bundle, String message) {
        bundle.putString(PARAMS_MESSAGE, message);
    }

    /**
     * 传入是否点击外边缘取消
     *
     * @param bundle     bundle对象
     * @param cancelable Boolean类型对象
     */
    protected static void putCancelableParam(Bundle bundle, boolean cancelable) {
        bundle.putBoolean(PARAMS_IS_CANCEL, cancelable);
    }
}
