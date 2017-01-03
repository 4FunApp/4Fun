package com.joker.fourfun.ui.mode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.joker.fourfun.R;
import com.joker.fourfun.ui.mode.contract.ModeState;

/**
 * Created by joker on 2017/1/3.
 */

public class DayState implements ModeState {
    @Override
    public void setTheme(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

        activity.setTheme(R.style.DayAppTheme);
        activity.recreate();
    }
}
