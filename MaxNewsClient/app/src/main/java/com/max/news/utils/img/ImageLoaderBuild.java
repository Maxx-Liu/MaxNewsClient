package com.max.news.utils.img;

import android.widget.ImageView;

import com.bumptech.glide.Priority;

/**
 * @auther MaxLiu
 * @time 2017/1/21
 */

public interface ImageLoaderBuild {

    /**
     * 设置加载中占位图
     *
     * @param drawableId drawable/...
     */
    void setPlaceHolder(int drawableId);

    /**
     * 设置加载出错占位图
     *
     * @param drawableId drawable/...
     */
    void setError(int drawableId);

    /**
     * 设置路径
     *
     * @param path 网络图片路径/gif路径/本地视频路径
     */
    void setPath(String path);

    /**
     * 设置注入的ImageView控件实例
     *
     * @param imageView 控件实例
     */
    void intoImageView(ImageView imageView);

    /**
     * 设置内存缓存
     *
     * @param type 默认都缓存(不设置)
     *             MEMORY_NO:只内存
     *             DISK_NO:只磁盘
     *             ALL_NO:都不缓存
     */
    void setCache(int type);

    /**
     * 设置缓存优先级
     *
     * @param priority HIGH : 高
     *                 NORMAL : 一般
     *                 LOW : 低
     *                 IMMEDIATE : 最高
     */
    void setPriority(Priority priority);

    /**
     * 设置显示动画
     *
     * @param anim 自定义的动画(不设置使用默认动画)
     */
    void setAnimation(int anim);

    /**
     * 加载之前加载缩略图
     */
    void loadThumbnail();

    /**
     * 设置缩略图
     */
    void loadThumbnail(float i);

    /**
     * 执行加载
     */
    void load();

    /**
     * 执行加载
     */
    void loadGif();

    /**
     * 执行加载
     */
    void loadVideo();
}
