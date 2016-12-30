package com.joker.fourfun.utils;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by joker on 2016/11/28.
 */

public class RxUtil {
    /**
     * rxjava 线程封装
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerTransformer() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * rxjava 超时抛出异常
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxTimeoutTransformer() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.timeout(3, TimeUnit.SECONDS);
            }
        };
    }
}
