package com.max.news.db.cache;

import com.max.news.MVP.home.channelist.pojo.ChannelInfoBean;
import com.max.news.MVP.home.channelist.pojo.ChannelListResBody;
import com.max.news.db.http.HttpResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;

/**
 * RxCache管理接口,用于缓存请求道的数据,需要与Rxjava和Retrofit配合使用
 *
 * @auther MaxLiu
 * @time 2017/1/17
 * @see com.max.news.db.http.ApiService
 */

public interface CacheProviders {

    /**
     * 频道标题列表的请求缓存,缓存时长2分钟
     * @param channelList 请求标题列表的Observable
     * @param userName 使用者名称(目前还没有登录供能,设置为“titles”)
     * @param evictDynamicKey
     * @return
     */
    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<HttpResult<ChannelListResBody>>> getChannelList(
            Observable<HttpResult<ChannelListResBody>> channelList,
            DynamicKey userName,
            EvictDynamicKey evictDynamicKey
            );

    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<HttpResult<ChannelListResBody>>> getChannelList(
            Observable<HttpResult<ChannelListResBody>> channelList,
            DynamicKey userName,
            EvictProvider evictProvider
    );

    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<HttpResult<ChannelListResBody>>> getChannelList(
            Observable<HttpResult<ChannelListResBody>> channelList,
            EvictProvider evictProvider
    );


    /**
     * 缓存每个频道的新闻信息列表
     * @param channelInfo 新闻频道列表信息内容
     * @param channelTitle 缓存key,设定为频道列表标题
     * @param evictDynamicKey
     * @return
     */
    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<HttpResult<ChannelInfoBean>>> getChannelInfo(
            Observable<HttpResult<ChannelInfoBean>> channelInfo,
            DynamicKey channelTitle,
            EvictDynamicKey evictDynamicKey
    );
}
