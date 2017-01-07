package com.max.news.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Glide图片加载工具封装
 *
 * @auther MaxLiu
 * @time 2016/12/19
 */

public class GliderUtil {

    /**
     * 设置网络图片
     * @param context 上下文（Context,Activity,Fragment,FragmentActivity）
     * @param path 网络地址
     * @param imageView ImageView控件
     */
    public static void loadHttpImage(Context context, String path, ImageView imageView){
        Glide.with(context).load(path).into(imageView);
    }

}
