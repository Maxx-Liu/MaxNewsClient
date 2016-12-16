package com.max.news.http;

import com.max.news.pojo.ChannelInfo;
import com.max.news.pojo.ChannelListResBody;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Retrofit请求接口，定义URL后面的字段和需要传入的值，方便其余方法调用
 */

public interface ApiService {
    @GET(Url.URL_CHANNEL_NEWS)
    Observable<ChannelListResBody> getChannelList();

    @GET(Url.URL_SEARCH_NEWS)
    Observable<ChannelInfo> getChannelInfo(
            @Query("channelId") String mChannelId,
            @Query("channelName") String mChannelName,
            @Query("title") String mChannelTitle,
            @Query("page") String mPage,
            @Query("needContent") String mNeedContent,
            @Query("needHtml") String mNeedHtml,
            @Query("needAllList") String mNeedAllList,
            @Query("needResult") String mNeedResult
    );
}
