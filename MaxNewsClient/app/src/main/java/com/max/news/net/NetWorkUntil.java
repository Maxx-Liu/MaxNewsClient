package com.max.news.net;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.max.news.BuildConfig;
import com.max.news.net.api.APISet;
import com.max.news.pojo.ChannelListBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class NetWorkUntil {
    private static final String TAG = "NetWorkUntil";
    private static INewsService mIChannelList;

    private static Retrofit createRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9,TimeUnit.SECONDS);

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return new Retrofit.Builder().baseUrl(APISet.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static void requestNetWork(){
        INewsService mApi = createRetrofit().create(INewsService.class);
        mApi.getChannelList(APISet.APP_ID, APISet.SIGN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChannelListBean>() {
                               @Override
                               public void accept(ChannelListBean channelListBean) throws Exception {
                                   Log.e(TAG,"请求成功");
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   Log.e(TAG,"请求失败" + throwable.toString());
                               }
                           });
//        new Observer<ChannelListBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ChannelListBean value) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG,"请求失败" + e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG,"请求成功");
//                    }
//                });
    }
//    private static void requestNet(){
//        Interceptor mInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain
//                        .request()
//                        .newBuilder()
//                        .addHeader("showapi_appid", "28706")
//                        .addHeader("showapi_sign", "f1ecc54fe30a4504831a23f8b63cb532")
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        };

//        OkHttpClient client = new OkHttpClient();
//        client.interceptors().add(mInterceptor);

//        Retrofit mRetrofit = new Retrofit.Builder()
//                .baseUrl(APISet.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(new OkHttpClient())
//                .build();
//        mIChannelList = mRetrofit.create(INewsService.class);
//        Call<ChannelListBean> mCall = mIChannelList.getChannelList(APISet.APP_ID, APISet.SIGN);
//        mCall.enqueue(new Callback<ChannelListBean>(){
//
//            @Override
//            public void onResponse(Call<ChannelListBean> call, Response<ChannelListBean> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ChannelListBean> call, Throwable t) {
//
//            }
//        });
//    }
//
//    public static Call<ChannelListBean> getChannelListCall(){
//        requestNet();
//        return  mIChannelList.getChannelList(APISet.APP_ID, APISet.SIGN);
//    }
}
