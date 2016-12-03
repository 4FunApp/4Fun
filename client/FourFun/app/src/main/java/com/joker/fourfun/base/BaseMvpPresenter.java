package com.joker.fourfun.base;

/**
 * Created by joker on 2016/11/27.
 */

public abstract class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detach() {
        mView = null;
    }
}
