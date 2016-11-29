package com.joker.fourfun.di.module;

import android.app.Application;

import com.joker.fourfun.FourFun;
import com.joker.fourfun.utils.RetrofitUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joker on 2016/11/28.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    FourFun provideFourFun() {
        return (FourFun) this.application;
    }

    @Singleton
    @Provides
    RetrofitUtil provideRetrofitUtil() {
        return new RetrofitUtil();
    }
}
