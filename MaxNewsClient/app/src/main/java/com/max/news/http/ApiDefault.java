package com.max.news.http;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 初始化Http请求，添加必须的一些系统参数以及Header
 *
 * @auther MaxLiu
 * @time 2016/12/16
 */

public class ApiDefault {

    private static ApiService SERVICE;
    //请求超时
    private static final int DEFAULT_TIME_OUT = 10000;
    //时间格式
    private static final String TIME_STYLE = "yyyyMMddHHmmss";
    //系统参数请求名
    private static final String PARAMS_APP_ID = "showapi_appid";//app id
    private static final String PARAMS_SIGN = "showapi_sign";//签名
    private static final String PARAMS_TIME = "showapi_timestamp";//客户端时间
    private static final String PARAMS_SING_METHOD = "showapi_sign_method";//加密方式
    private static final String PARAMS_RES_GZIP = "showapi_res_gzip";//是否压缩1.压缩0.不压缩
    //系统参数请求值
    private static final String APP_ID = "28715";//app id
    private static final String SIGN = "f1ecc54fe30a4504831a23f8b63cb532";//签名
    private static final String SIGN_METHOD = "md5";//其值可选为"md5"或"hmac"
    public static final String RES_GZIP = "0";//是否压缩1.压缩0.不压缩

    public  static ApiService getApiDefault(){
        if(SERVICE == null){
            //手动创建 OkHttpClient 并设置超时时间
            final OkHttpClient.Builder httpClinetBuilder = new OkHttpClient().newBuilder();
            httpClinetBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
            //对所有请求添加请求头（header）  Interceptor(拦截器)
            httpClinetBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    //通过Chain(链)获取请求
                    Request request = chain.request();

                    Request.Builder requestBuilder = request.newBuilder();
                    //添加表单
//                    RequestBody formBody = new FormBody.Builder()
//                            .add("1","2")
//                            .build();
                    //添加统一系统级参数
                    HttpUrl.Builder httpUrlBuilder = request.url()
                            .newBuilder()
                            .addQueryParameter(PARAMS_APP_ID,APP_ID)
                            .addQueryParameter(PARAMS_SIGN,SIGN)
                            .addQueryParameter(PARAMS_TIME,getCurrentTime())
                            .addQueryParameter(PARAMS_SING_METHOD,SIGN_METHOD)
                            .addQueryParameter(PARAMS_RES_GZIP,"0");

                    Request newRequest = request.newBuilder()
                            //对所有请求添加请求头
                            //.header("mobileFlag", "adfsaeefe").addHeader("type", "4")
                            .method(request.method(), request.body())
                            .url(httpUrlBuilder.build())
                            .build();

                    return  chain.proceed(newRequest);
                }
            });

            SERVICE = new Retrofit.Builder()
                    .client(httpClinetBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Url.BASE_URL)
                    .build()
                    .create(ApiService.class);
        }
        return SERVICE;
    }

    /**
     * 获取客户端当前时间
     * @return 格式 yyyyMMddHHmmss
     */
    private static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                TIME_STYLE, Locale.CHINA);
        return dateFormat.toString();
    }
}
