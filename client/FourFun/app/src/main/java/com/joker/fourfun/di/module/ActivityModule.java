package com.joker.fourfun.di.module;

import android.support.v7.app.AppCompatActivity;

import com.joker.fourfun.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joker on 2016/11/28.
 */
@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        mActivity = appCompatActivity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideActivity() {
        return mActivity;
    }
}
