package com.max.news.utils.img;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.max.news.R;

import java.io.File;

/**
 * Glide建造者
 *
 * @auther MaxLiu
 * @time 2017/1/21
 */

public class GlideBuilder implements ImageLoaderBuild{
    //都不缓存
    public static final int ALL_NO = 1;
    //不缓存内存
    public static final int MEMORY_NO = 2;
    //不缓存磁盘
    public static final int DISK_NO = 3;
    //都缓存
    private static final int ALL = 1;

    //默认的加载中占位图
    private static final int DEFAULT_PLACEHOLDER = R.drawable.placeholder;
    //默认的加载失败占位图
    private static final int DEFAULT_ERROR = R.drawable.loading_falied;

    private int mPlaceHolder;
    private int mError;
    private int mAnimation;
    private boolean isSkipMemory = false;
    private float mFloat = 1f;
    private Context mContext;
    private String mPath;
    private ImageView mImageView;
    private Priority mPriority = Priority.NORMAL;
    private DiskCacheStrategy mDiskCacheStrategy = DiskCacheStrategy.ALL;

    public GlideBuilder(Context context){
        mPlaceHolder = DEFAULT_PLACEHOLDER;
        mError = DEFAULT_ERROR;
        mContext = context;
    }

    @Override
    public void setPlaceHolder(int drawableId) {

    }

    @Override
    public void setError(int drawableId) {

    }

    @Override
    public void setPath(String path) {
        mPath = path;
    }

    @Override
    public void intoImageView(ImageView imageView) {
        mImageView = imageView;
    }

    @Override
    public void setCache(int type) {
        if(type == ALL_NO){
            mDiskCacheStrategy = DiskCacheStrategy.NONE;
            isSkipMemory = true;
        }else if(type == MEMORY_NO){
            mDiskCacheStrategy = DiskCacheStrategy.ALL;
            isSkipMemory = true;
        }else if(type == DISK_NO){
            mDiskCacheStrategy = DiskCacheStrategy.NONE;
            isSkipMemory = false;
        }else{
            mDiskCacheStrategy = DiskCacheStrategy.ALL;
            isSkipMemory = true;
        }
    }

    @Override
    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    @Override
    public void setAnimation(int anim) {

    }

    @Override
    public void loadThumbnail() {
    }

    @Override
    public void loadThumbnail(float i) {
        mFloat = i;
    }

    /**
     * 普通加载没有GIF,Video,Animation
     */
    @Override
    public void load(){
        Glide.with(mContext)
                .load(mPath)
                .placeholder(mPlaceHolder)
                .error(mError)
                .skipMemoryCache(isSkipMemory)
                .diskCacheStrategy(mDiskCacheStrategy)
                .priority(mPriority)
                .thumbnail(mFloat)
                .into(mImageView);
    }

    /**
     * 加载GIF
     */
    @Override
    public void loadGif(){
        Glide.with(mContext)
                .load(mPath)
                .asGif()
                .placeholder(mPlaceHolder)
                .error(mError)
                .skipMemoryCache(isSkipMemory)
                .diskCacheStrategy(mDiskCacheStrategy)
                .priority(mPriority)
                .thumbnail(mFloat)
                .into(mImageView);
    }

    /**
     * 加载本地视频
     */
    @Override
    public void loadVideo(){
        Glide.with(mContext)
                .load(Uri.fromFile(new File(mPath)))
                .asGif()
                .placeholder(mPlaceHolder)
                .error(mError)
                .skipMemoryCache(isSkipMemory)
                .diskCacheStrategy(mDiskCacheStrategy)
                .priority(mPriority)
                .thumbnail(mFloat)
                .into(mImageView);
    }

}
