package com.max.news.utils.img;

import android.content.Context;
import android.widget.ImageView;

import com.max.news.utils.BaseImageViewLoaderUtil;

/**
 * @auther MaxLiu
 * @time 2017/1/16
 */

public class GlideLoaderUtil implements BaseImageViewLoaderUtil {

    @Override
    public void loadImage(String url, ImageView imageView) {

    }

    @Override
    public void loadImage(String url, int placeholder, ImageView imageView) {

    }

    @Override
    public void loadImage(Context context, String url, int placeholder, ImageView imageView) {

    }

    @Override
    public void loadGifImage(String url, int placeholder, ImageView imageView) {

    }

//    @Override
//    public void loadImageWithProgress(String url, ImageView imageView, ProgressLoadListener listener) {
//
//    }
//
//    @Override
//    public void loadGifWithProgress(String url, ImageView imageView, ProgressLoadListener listener) {
//
//    }

    @Override
    public void clearImageDiskCache(Context context) {

    }

    @Override
    public void clearImageMemoryCache(Context context) {

    }

    @Override
    public void trimMemory(Context context, int level) {

    }

    @Override
    public String getCacheSize(Context context) {
        return null;
    }
}
