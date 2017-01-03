package com.joker.fourfun.ui.mode;

import android.app.Activity;

import com.joker.fourfun.ui.mode.contract.ModeState;

/**
 * Created by joker on 2017/1/3.
 */

public class ThemeModeContext {
    ModeState mState = new NightState();
    private static ThemeModeContext instance = null;

    private ThemeModeContext() {
    }

    public static ThemeModeContext getInstance() {
        if (instance == null) {
            synchronized (ThemeModeContext.class) {
                if (instance == null) {
                    instance = new ThemeModeContext();
                }
            }
        }

        return instance;
    }

    public void setAppTheme(Activity activity) {
        mState.setTheme(activity);
    }

    public void setState(ModeState state) {
        mState = state;
    }
}
