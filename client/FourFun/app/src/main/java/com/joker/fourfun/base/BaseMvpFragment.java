package com.joker.fourfun.base;

import android.app.Activity;

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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initInject();
        mActivity = (SupportActivity) activity;
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
        mPresenter.detach();
        super.onDestroy();
    }

    protected abstract void initInject();
}
