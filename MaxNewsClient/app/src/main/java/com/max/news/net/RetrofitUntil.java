package com.max.news.net;

import com.max.news.pojo.ChannelListBean;
import com.max.news.net.api.APISet;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class RetrofitUntil {
    private static INewsService mIChannelList;

    private static void requestNet(){
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

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(APISet.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        mIChannelList = mRetrofit.create(INewsService.class);
    }

    public static Call<ChannelListBean> getChannelListCall(){
        requestNet();
        return  mIChannelList.getChannelList(APISet.APP_ID, APISet.SIGN);
    }
}
