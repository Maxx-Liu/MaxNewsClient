package com.max.news.http;

import com.max.news.ui.ActivityLifeCycleEvent;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * 对获取的Data Error Code进行预处理
 *
 * @auther MaxLiu
 * @time 2016/12/16
 * @see HttpResult
 */

public class RxHelper {

    public static <T> Observable.Transformer<HttpResult<T>, T> handleResult(
            final ActivityLifeCycleEvent event,
            final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject){
        return new Observable.Transformer<HttpResult<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResult<T>> tObservable) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                    lifecycleSubject.first(new Func1<ActivityLifeCycleEvent, Boolean>() {
                        @Override
                        public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
                            return activityLifeCycleEvent.equals(event);
                        }
                    });
                return tObservable.flatMap(new Func1<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResult<T> tHttpResult) {
                        if(tHttpResult.getCode() == 0){
                            return createData(tHttpResult.getData());
                        }else{
                            return Observable.error(new ApiException(tHttpResult.getError()));
                        }
                    }
                }).takeUntil(compareLifecycleObservable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data 请求到的数据
     * @param <T> 任何类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });
    }
}
