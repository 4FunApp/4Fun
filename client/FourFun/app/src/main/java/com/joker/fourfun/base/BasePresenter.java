package com.joker.fourfun.base;

/**
 * Created by joker on 2016/11/27.
 */

public interface BasePresenter<T extends BaseView> {
    void attach(T mView);

    void detach();
}
