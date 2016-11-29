package com.joker.fourfun.utils;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.schedulers.IoScheduler;

/**
 * Created by joker on 2016/11/28.
 */

public class RxUtil {
    public static <T> FlowableTransformer<T, T> rxSchedulerTransformer() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(new IoScheduler())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
