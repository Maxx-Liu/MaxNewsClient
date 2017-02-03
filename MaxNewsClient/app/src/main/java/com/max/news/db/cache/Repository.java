package com.max.news.db.cache;

import android.util.Log;

import com.max.news.MVP.home.channelist.bean.ChannelInfoBean;
import com.max.news.MVP.home.channelist.bean.ChannelListResBody;
import com.max.news.db.http.ApiException;
import com.max.news.db.http.ApiService;
import com.max.news.db.http.HttpResult;
import com.max.news.db.http.RetrofitUtil;
import com.max.news.db.http.RxHelper;
import com.max.news.db.http.Url;
import com.trello.rxlifecycle.components.RxActivity;

import java.io.File;
import java.net.URL;
import java.util.Map;

import io.rx_cache.DynamicKey;
import io.rx_cache.DynamicKeyGroup;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @auther MaxLiu
 * @time 2017/2/3
 */

public class Repository {

    private static CacheProviders cacheProviders;
    private static ApiService api;

    public static void init(File cacheDir) {
        if(cacheDir != null) {
            cacheProviders = new RxCache.Builder()
                    .persistence(cacheDir, new GsonSpeaker())
                    .using(CacheProviders.class);

            api = RetrofitUtil.getRetrofit().create(ApiService.class);
        }
    }

    /**
     * 获取所有的频道标题
     *
     * @param userName 用户名  用于缓存
     * @param update   是否重新请求网络获取数据
     * @return Observable对象
     */
    public Observable<ChannelListResBody> getChannels(
            final String userName,  //缓存key，不同用户数据不同
            final boolean update  //是否重新从网络获取
    ) {
        return cacheProviders.getChannelList(
                api.getChannelList(),
                new DynamicKey(userName),
                new EvictDynamicKey(update)
        ).compose(Repository.<ChannelListResBody>handleResult());
    }

    /**
     * 获取某个频道/标题/关键字列表
     *
     * @param map      接口请求信息
     * @param userName 用户名
     * @param update 是否重新网络请求获取
     * @return Observable<ChannelInfoBean>
     */
    public Observable<ChannelInfoBean> getChannelInfo(
            final Map<String, String> map,
            final String userName,
            final int page,
            final boolean update
    ) {
        Log.e("info", "page : " + map.get(map.get(Url.PARAM_CHANNEL_PAGE)));
        return cacheProviders.getChannelInfo(
                api.getChannelInfo(map),
                new DynamicKeyGroup(
                        userName,
                        page
                ),
                new EvictDynamicKey(update)
        ).compose(Repository.<ChannelInfoBean>handleResult());
    }

    /**
     * 利用泛型方法处理Observable，使获取后的Observable只包含数据，省去了每次请求的解封装
     *
     * @param <T> 声明一个泛型 T
     * @return Observable.Transformer<HttpResult<T>,T>对象
     */
    private static <T> Observable.Transformer<Reply<HttpResult<T>>, T> handleResult() {
        return new Observable.Transformer<Reply<HttpResult<T>>, T>() {
            @Override
            public Observable<T> call(Observable<Reply<HttpResult<T>>> httpResultObservable) {
                return httpResultObservable.flatMap(
                        new Func1<Reply<HttpResult<T>>,
                                Observable<T>>() {
                            @Override
                            public Observable<T> call(Reply<HttpResult<T>> tHttpResult) {
                                if (tHttpResult.getData().getCode() == 0) {
                                    return createData(tHttpResult.getData().getData());
                                } else {
                                    return Observable.error(
                                            new ApiException(tHttpResult.getData().getError())
                                    );
                                }
                            }
                        }).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data 请求到的数据
     * @param <T>  任何类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });
    }
}
