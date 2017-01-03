package com.joker.fourfun.utils;

import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.net.LoginException;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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
     *
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

    /**
     * 登录注册状态校验
     *
     * @param code
     * @return
     */
    public static FlowableTransformer<List<LoginInfo>, LoginInfo> rxStateCheck(final int code) {
        return new FlowableTransformer<List<LoginInfo>, LoginInfo>() {
            @Override
            public Publisher<LoginInfo> apply(Flowable<List<LoginInfo>> upstream) {
                return upstream
                        .map(new Function<List<LoginInfo>, LoginInfo>() {
                            @Override
                            public LoginInfo apply(List<LoginInfo> loginInfos) throws Exception {
                                int serverCode = loginInfos.get(0).getCode();
                                if (serverCode != code) {
                                    throw new LoginException(serverCode);
                                }

                                return loginInfos.get(0);
                            }
                        });
            }
        };
    }
}
