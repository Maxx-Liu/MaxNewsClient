package com.max.news.db.http;

import com.max.news.db.cache.CacheProviders;
import com.max.news.utils.FileUtil;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import rx.functions.Func1;

/**
 * @auther MaxLiu
 * @time 2017/1/17
 */

public class HttpData extends RetrofitUtil{

    private static File cacheDirectory = FileUtil.getCacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder()
            .persistence(cacheDirectory, new JolyglotGenerics() {
                @Override
                public String toJson(Object src, Type typeOfSrc) {
                    return null;
                }

                @Override
                public <T> T fromJson(String json, Type type) throws RuntimeException {
                    return null;
                }

                @Override
                public <T> T fromJson(File file, Type typeOfT) throws RuntimeException {
                    return null;
                }

                @Override
                public GenericArrayType arrayOf(Type componentType) {
                    return null;
                }

                @Override
                public ParameterizedType newParameterizedType(Type rawType, Type... typeArguments) {
                    return null;
                }

                @Override
                public String toJson(Object src) {
                    return null;
                }

                @Override
                public <T> T fromJson(String json, Class<T> classOfT) throws RuntimeException {
                    return null;
                }

                @Override
                public <T> T fromJson(File file, Class<T> classOfT) throws RuntimeException {
                    return null;
                }
            })
            .using(CacheProviders.class);
    protected static final ApiService service = getRetrofit().create(ApiService.class);

    //在访问 HttpMethods 时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }

//    public void getChannelList(Observable<HttpResult<ChannelListResBody>> observable){
//        Observable mObservable = service.getChannelList()
//                .map(new HttpResultFunc<HttpResult<ChannelListResBody>>());
//        Observable observableCache = providers.getChannelList(
//                mObservable,
//                new DynamicKey("titles"),
//                new EvictDynamicKey(false))
//                .map(new HttpResultFuncCcche<HttpResult<ChannelListResBody>>());
//    }

//    public void getChannelInfo(Observable<HttpResult<ChannelListResBody>> observable){
//        Observable mObservable = service.getChannelInfo();
//        Observable observableCache = providers.getChannelInfo(
//                mObservable,
//                new DynamicKey("titles"),
//                new EvictDynamicKey(false))
//                .map(new HttpResultFuncCcche<HttpResult<ChannelListResBody>>());
//    }

//    /**
//     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
//     *
//     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
//     */
//    private  class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
//
//        @Override
//        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getCode() !=1 ) {
//                throw new ApiException(httpResult);
//            }
//            return httpResult.getData();
//        }
//    }

    /**
     * 用来统一处理RxCacha的结果
     */
    private  class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }
}
