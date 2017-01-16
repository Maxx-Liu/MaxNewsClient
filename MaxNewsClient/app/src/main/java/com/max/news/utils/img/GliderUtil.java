package com.max.news.utils.img;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Glide图片加载工具封装
 * <p>
 * Google推荐,context可以传多种类型(Glide的with方法不光接受Context,还接受Activity 和 Fragment,
 * Context会自动的从他们获取),将Activity/Fragment作为with()参数的好处是:图片加载会和Activity
 * /Fragment的生命周期保持一致(比如 Paused状态在暂停加载,在Resumed的时候又自动重新加载),缓存大小为
 * ImageView大小,支持GIF(尽量不要再非主线程使用Glide).
 * <p>
 * 缺点 : 加载的图片质量要差于Picasso(默认的Bitmap格式是RGB_565,节省内存开销)可配置:
 * {@link GlideModuleConfig#applyOptions(Context, GlideBuilder)}
 *
 * @auther MaxLiu
 * @time 2016/12/19
 */

public class GliderUtil {

    /**
     * 设置网络图片
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     */
    public static void loadHttpImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载网络图片,并设置占位图和加载失败图
     *
     * @param context   上下文
     * @param url       url路径
     * @param imageView ImageView
     * @param cache     占位图资源
     * @param error     加载失败图资源
     */
    public static void loadHttpImage(Context context, String url, ImageView imageView
            , int cache, int error) {
        Glide.with(context)
                .load(url)
                .placeholder(cache)
                .error(error)
                .into(imageView);
    }

    /**
     * 不使用Glide默认动画加载
     * 用于解决有的图片第一次加载的时候只显示占位图，第二次才显示正常的问题（自定义圆形ImageView）
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     */
    public static void loadNoAnimate(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .dontAnimate()
                //.placeholder(R.drawable.loading_spinner)
                .into(imageView);
    }

    /**
     * 同时缓存图片原来大小和ImageView大小
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     */
    public static void loadCacheAll(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 是否禁止磁盘缓存加载图片
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     * @param type      缓存的类型
     *                  <li>磁盘缓存全部 DiskCacheStrategy.ALL</li>
     *                  <li>磁盘禁止缓存DiskCacheStrategy.NONE</li>
     */
    public static void loadImage(String url, Context context, ImageView imageView, DiskCacheStrategy type) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(type)
                .into(imageView);
    }

    /**
     * 是否禁止内存缓存加载图片
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     * @param skipMemoryCache 禁止内存缓存 true为禁止
     */
    public static void loadImage(String url, Context context, ImageView imageView, boolean skipMemoryCache) {
        Glide.with(context)
                .load(url)
                .skipMemoryCache(skipMemoryCache)
                .into(imageView);
    }

    /**
     * 是否禁止内存/磁盘缓存加载图片
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     * @param type            缓存的类型
     *                        <li>磁盘缓存全部 DiskCacheStrategy.ALL</li>
     *                        <li>磁盘禁止缓存DiskCacheStrategy.NONE</li>
     * @param skipMemoryCache 禁止内存缓存 true为禁止
     */
    public static void loadImage(String url, Context context, ImageView imageView, DiskCacheStrategy type,
                                 boolean skipMemoryCache) {
        Glide.with(context)
                .load(url)
                .skipMemoryCache(skipMemoryCache)
                .diskCacheStrategy(type)
                .into(imageView);
    }

    /**
     * 清除内存中的缓存 必须在UI线程中调用
     *
     * @param context 上下文
     */
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘中的缓存 必须在后台线程中调用，建议同时clearMemory()
     *
     * @param context 上下文
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 优先级加载图片
     *
     * @param context   上下文（Context,Activity,Fragment,FragmentActivity）建议ApplicationContext
     * @param url       网络地址
     * @param imageView ImageView控件
     * @param priority  优先级  Priority.LOW/Priority.HIGH
     */
    public static void loadImageWithPriority(String url, Context context,
                                             ImageView imageView, Priority priority) {
        Glide.with(context)
                .load(url)
                .priority(priority)
                .into(imageView);
    }
}
