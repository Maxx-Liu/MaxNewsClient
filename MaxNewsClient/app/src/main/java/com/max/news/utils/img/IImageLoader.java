package com.max.news.utils.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * 定义项目需要实现的图片加载功能接口
 *
 * @auther MaxLiu
 * @time 2017/1/16
 */

public interface IImageLoader {
    //无占位图
    void loadImage(String url, ImageView imageView);

    void loadImage(String url, int placeholder, ImageView imageView);

    void loadImage(Context context, String url, int placeholder, ImageView imageView);

    void loadGifImage(String url, int placeholder, ImageView imageView);

//    void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener);
//
//    void loadGifWithProgress(String url, ImageView imageView, ProgressLoadListener listener);

    //清除硬盘缓存
    void clearImageDiskCache(final Context context);

    //清除内存缓存
    void clearImageMemoryCache(Context context);

    //根据不同的内存状态，来响应不同的内存释放策略
    void trimMemory(Context context, int level);

    //获取缓存大小
    String getCacheSize(Context context);


}
