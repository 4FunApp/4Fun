package com.joker.fourfun.ui.mode;

import android.app.Activity;

import com.joker.fourfun.R;
import com.joker.fourfun.ui.mode.contract.ModeState;

/**
 * Created by joker on 2017/1/3.
 */

public class NightState implements ModeState {
    @Override
    public void setTheme(Activity activity) {
        activity.setTheme(R.style.AppTheme);
        activity.recreate();
    }
}
