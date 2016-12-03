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
    public T mPresenter;
    public ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mComponent = DaggerActivityComponent.builder().activityModule(new ActivityModule
                (this)).appComponent(AppComponent.getInstance()).build();
        initLayout();
        ButterKnife.bind(this);
//        mPresenter = initPresenter();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    // 实例化presenter
//    protected abstract T initPresenter();

    // 设置布局文件
    protected abstract void initLayout();
}
