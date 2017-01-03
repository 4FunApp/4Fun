package com.joker.fourfun.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joker.fourfun.di.component.AppComponent;
import com.joker.fourfun.di.component.DaggerFragmentComponent;
import com.joker.fourfun.di.component.FragmentComponent;
import com.joker.fourfun.di.module.FragmentModule;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseMvpFragment<V extends BaseView, T extends BaseMvpPresenter<V>> extends
        SupportFragment implements
        BaseView {
    @Inject
    protected T mPresenter;
    protected SupportActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        return createView(inflater, container, savedInstanceState);
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState);

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initInject();
        mActivity = _mActivity;
        mPresenter.attach((V) this);
    }

    protected FragmentComponent getComponent() {
        return DaggerFragmentComponent.builder().appComponent(AppComponent.getInstance()).fragmentModule
                (getModule()).build();
    }

    protected FragmentModule getModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.cancelAllSubscription();
        mPresenter.detach();
        super.onDestroy();
    }

    protected abstract void initInject();
}
