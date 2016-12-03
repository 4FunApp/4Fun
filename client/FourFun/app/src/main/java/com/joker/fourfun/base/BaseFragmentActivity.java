package com.joker.fourfun.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragmentActivity extends SupportActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (savedInstanceState != null) {
            loadRootFragment(getContainerId(), getToFragment());
        }
    }

    protected abstract SupportFragment getToFragment();

    protected abstract int getContainerId();
}
