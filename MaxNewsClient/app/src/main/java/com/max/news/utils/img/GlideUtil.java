package com.max.news.utils.img;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.max.news.R;

import java.io.File;

/**
 * Gilde的工具类，几乎包括了Glide的所有加载功能
 *
 * @auther MaxLiu
 * @time 2017/1/16
 */

public class GlideUtil {
    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */

    /**
     * 默认加载图片
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .into(mImageView);
    }

    /**
     * 加载指定大小的图片
     *
     * @param mContext   上下文
     * @param path       路径
     * @param width      指定的宽度
     * @param height     指定的高度
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .override(width, height)
                .into(mImageView);
    }

    /**
     * 自定义占位图
     *
     * @param mContext       上下文
     * @param path           路径
     * @param mImageView     注入的ImageView
     * @param loadingImage   加载中的占位图
     * @param errorImageView 加载失败的占位图
     */
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int loadingImage, int errorImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(loadingImage)
                .error(errorImageView)
                .into(mImageView);
    }

    /**
     * 设置加载图片大小,并设置占位图
     *
     * @param mContext   上下文
     * @param path       路径
     * @param width      指定的宽度
     * @param height     指定的高度
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext)
                .load(path)
                .override(width, height)
                .placeholder(lodingImage)
                .error(errorImageView)
                .into(mImageView);
    }

    /**
     * 设置不进行内存缓存
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .skipMemoryCache(true)
                .into(mImageView);
    }

    /**
     * 设置下载优先级
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .priority(Priority.NORMAL)
                .into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    /**
     * 自定义缓存策略
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView);
    }

    /**
     * 自定义加载动画
     *
     * @param mContext   上下文
     * @param path       路径
     * @param anim       自定义动画
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .animate(anim)
                .into(mImageView);
    }

    /**
     * 加载图片之前先加载缩略图
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .thumbnail(0.1f)
                .into(mImageView);
    }

    /**
     * 缩放后加载图片
     * centerCrop() : 裁剪,即缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分(可能会显示不完全)
     * fitCenter()  : 裁剪,即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围(可能不会填满整个 ImageView)
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .centerCrop()
                .into(mImageView);
    }

    /**
     * 加载GIF动态图
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .asGif()//判断url是否为Gif地址,否则显示error图
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .into(mImageView);
    }

    /**
     * 加载GIF静态图(第一帧)
     *
     * @param mContext   上下文
     * @param path       路径
     * @param mImageView 注入的ImageView
     */
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext)
                .load(path)
                .asBitmap()//Gif转为Bitmap(第一帧)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .into(mImageView);
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImageViewListener(Context mContext, String path, ImageView mImageView, RequestListener<String, GlideDrawable> requstlistener) {
        Glide.with(mContext)
                .load(path)
                .listener(requstlistener)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .into(mImageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(mContext)
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .into(simpleTarget);
    }

    /**
     * 加载本地视频
     *
     * @param mContext  上下文
     * @param filePath  本地的视频路径
     * @param imageView 显示的ImageView
     */
    public static void loadLocalVideo(Context mContext, String filePath, ImageView imageView) {
        Glide.with(mContext)
                .load(Uri.fromFile(new File(filePath)))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.loading_falied)
                .into(imageView);
    }

    /**
     * 清理磁盘缓存DiskCache(需要在子线程执行)
     *
     * @param mContext 上下文
     */
    public static void GuideClearDiskCache(Context mContext) {
        Glide.get(mContext)
                .clearDiskCache();
    }

    /**
     * 清理内存缓存MemoryCache(可以再UI线程执行)
     *
     * @param mContext 上下文
     */
    public static void GuideClearMemory(Context mContext) {
        Glide.get(mContext)
                .clearMemory();
    }

}
