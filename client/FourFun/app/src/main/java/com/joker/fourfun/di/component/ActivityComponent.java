package com.joker.fourfun.di.component;

import android.support.v7.app.AppCompatActivity;

import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.di.PerActivity;
import com.joker.fourfun.di.module.ActivityModule;
import com.joker.fourfun.ui.MainActivity;
import com.joker.fourfun.ui.SplashActivity;

import dagger.Component;

/**
 * Created by joker on 2016/11/28.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    AppCompatActivity activity();

    void inject(SplashActivity activity);

    void inject(MainActivity activity);
}
