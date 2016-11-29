package com.joker.fourfun.di.component;

import com.joker.fourfun.FourFun;
import com.joker.fourfun.di.module.AppModule;
import com.joker.fourfun.utils.RetrofitUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joker on 2016/11/28.
 */
@Singleton
@Component(modules = AppModule.class)
public abstract class AppComponent {
    private static AppComponent mComponent;

    public static AppComponent getInstance() {
        if (mComponent == null) {
            synchronized (AppComponent.class) {
                if (mComponent == null) {
                    mComponent = DaggerAppComponent.builder().appModule(new AppModule(FourFun.getInstance
                            ())).build();
                }
            }
        }

        return mComponent;
    }

    public abstract FourFun fourFun();

    public abstract RetrofitUtil retrofitUtil();
}
