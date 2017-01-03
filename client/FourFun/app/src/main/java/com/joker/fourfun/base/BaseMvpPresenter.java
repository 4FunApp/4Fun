package com.joker.fourfun.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by joker on 2016/11/27.
 */

public abstract class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {
    public T mView;
    public CompositeDisposable mDisposable;

    public void attach(T mView) {
        this.mView = mView;
        mDisposable = new CompositeDisposable();
    }

    public void detach() {
        mView = null;
    }

    public void addSubscription(Disposable disposable) {
        mDisposable.add(disposable);
    }

    public void cancelSubscription(Disposable disposable) {
        if (mDisposable != null && !disposable.isDisposed()) {
            mDisposable.remove(disposable);
        }
    }

    public void cancelAllSubscription() {
        if (mDisposable != null) {
            mDisposable.clear();
        }
    }
}
