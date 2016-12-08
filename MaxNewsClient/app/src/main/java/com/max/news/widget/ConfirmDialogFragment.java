package com.max.news.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.max.news.R;

/**
 *  A Custom Theme Style Dialog
 *
 *  @author Max Liu
 *  @since MaxNews-1.0.0
 */

public class ConfirmDialogFragment extends BaseDialogFragment {

    private static final String BUTTON_CONFIRM = "确认";
    private static final String BUTTON_CANCEL = "取消";

    private String mMessage;
    private ConfirmDialogListener mListener;

    /**
     * 确认对话框的listener
     */
    public interface ConfirmDialogListener extends BaseDialogListener,DialogInterface.OnClickListener{

    }

    /**
     * 通过初始化传入需要的title和message
     * @param title 标题
     * @param message 内容
     * @return ConfirmDialogFragment实例
     */
    public static ConfirmDialogFragment newInstance(String title, String message, boolean cancelable) {
        Bundle args = new Bundle();
        putTitleParam(args,title);
        putMessageParam(args,message);
        putCancelableParam(args,cancelable);
        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onReceiveDialogListener(BaseDialogListener listener) {
        if(listener instanceof ConfirmDialogListener){
            mListener = (ConfirmDialogListener)listener;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(!mIsCustomDialog) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(mTitle == null ? getString(R.string.app_name) : mTitle)
                    .setMessage(mContent == null ? "" : mContent)
                    .setPositiveButton(BUTTON_CONFIRM, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //确认按钮监听
                            mListener.onClick(dialog,which);
                        }
                    })
                    .setNegativeButton(BUTTON_CANCEL, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //取消按钮监听
                            mListener.onClick(dialog,which);
                        }
                    }).create();
        }else{
            return super.onCreateDialog(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mIsCustomDialog){
            View view = inflater.inflate(R.layout.dialog_custom,container,false);
            //启用窗体的扩展特性。
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            return view;
        }else{
            return super.onCreateView(inflater,container,savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void parseArgs(Bundle args) {
        super.parseArgs(args);
        mMessage = parseMsg();
    }

}
