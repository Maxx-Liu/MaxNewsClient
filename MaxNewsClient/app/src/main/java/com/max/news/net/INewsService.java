package com.max.news.net;

import com.max.news.pojo.ChannelListBean;
import com.max.news.net.api.APISet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.max.news.net.api.APISet.PARAMS_APP_ID;
import static com.max.news.net.api.APISet.PARAMS_SIGN;

/**
 * Retrofit请求接口，定义URL后面的字段和需要传入的值，方便其余方法调用
 */

interface INewsService {
    @GET(APISet.URL_CHANNEL_NEWS)
    Call<ChannelListBean> getChannelList(@Query(PARAMS_APP_ID) String appId,
                                         @Query(PARAMS_SIGN) String sign);
}
