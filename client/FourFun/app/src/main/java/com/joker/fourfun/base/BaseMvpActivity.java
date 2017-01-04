package com.joker.fourfun.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joker.fourfun.di.component.ActivityComponent;
import com.joker.fourfun.di.component.AppComponent;
import com.joker.fourfun.di.component.DaggerActivityComponent;
import com.joker.fourfun.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity<V extends BaseView, T extends BaseMvpPresenter<V>> extends
        AppCompatActivity {
    @Inject
    protected T mPresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentViewAndInject(savedInstanceState);
        ButterKnife.bind(this);
//        mPresenter = initPresenter();
        mPresenter.attach((V) this);
    }

    protected ActivityComponent getComponent() {
        return DaggerActivityComponent.builder().activityModule(getModule()).appComponent(AppComponent
                .getInstance()).build();
    }

    protected ActivityModule getModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    // 实例化presenter
//    protected abstract T initPresenter();

    // 设置布局文件
    protected abstract void setContentViewAndInject(Bundle savedInstanceState);
}
