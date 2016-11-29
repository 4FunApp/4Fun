package com.joker.fourfun;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by joker on 2016/11/27.
 */

public class FourFun extends Application {
    private static FourFun instance;

    public static FourFun getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
