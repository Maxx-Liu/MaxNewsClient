package com.max.news.db.http;

import com.max.news.base.ActivityLifeCycleEvent;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * @auther MaxLiu
 * @time 2016/12/16
 */

public class HttpUtil {

    private HttpUtil() {

    }

    /**
     * 在访问HttpUtil时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     *
     * @return HttpUtil对象
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //添加线程管理并订阅
    public void toSubscribe(Observable ob, Observer mObserver,
                            String cacheKey,final ActivityLifeCycleEvent event,
                            final PublishSubject<ActivityLifeCycleEvent> lifePublishSubject,
                            boolean isSave, boolean forceRefresh) {
        Observable.Transformer<HttpResult<Object>,Object> result =
                RxHelper.handleResult(event,lifePublishSubject);
        Observable observable = ob.compose(result)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                });

    }
}
