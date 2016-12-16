package com.max.news.http;

import com.orhanobut.hawk.Hawk;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 用来缓存请求得到的数据
 *
 * @auther MaxLiu
 * @time 2016/12/16
 */

public class RetrofitCache {

    /**
     * @param cacheKey 缓存的Key
     * @param fromNetwork
     * @param isSave       是否缓存
     * @param forceRefresh 是否强制刷新
     * @param <T>
     * @return
     */
    public static <T> Observable<T> load(final String cacheKey,
                                         Observable<T> fromNetwork,
                                         boolean isSave,
                                         boolean forceRefresh){
        Observable<T> fromCache = Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                //根据传入的key值获取缓存中的value
                T cache = (T)Hawk.get(cacheKey);
                if(cache != null){
                    //不为空，调用onNext传入数据
                    subscriber.onNext(cache);
                }else{
                    //为空，调用onComplete结束
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //是否缓存
        if(isSave){
            /**
             * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
             */
            //如果缓存，利用Rxjava的map实现缓存
            fromNetwork = fromNetwork.map(new Func1<T, T>() {
                @Override
                public T call(T t) {
                    Hawk.put(cacheKey,t);
                    return t;
                }
            });
        }
        //强制刷新
        if(forceRefresh){
            return fromNetwork;
        }else{
            //没有设置强制刷新，则使用concat获取数据链，并调用first表示只检索第一条数据
            return Observable
                    .concat(fromCache,fromNetwork)
                    .first(new Func1<T, Boolean>() {//返回值为空则检索下一条
                        @Override
                        public Boolean call(T t) {
                            return t != null;
                        }
                    });

        }
    }
}
