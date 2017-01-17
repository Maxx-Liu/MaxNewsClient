package com.max.news.db.http;

import com.max.news.db.cache.CacheProviders;
import com.max.news.utils.FileUtil;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.JolyglotGenerics;

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
}
