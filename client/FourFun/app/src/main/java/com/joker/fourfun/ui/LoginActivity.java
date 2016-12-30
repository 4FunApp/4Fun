package com.joker.fourfun.ui;

import android.os.Bundle;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.ui.fragment.LoginFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class LoginActivity extends SupportActivity {
    public static final int FIRST = 0;
    private BaseMvpFragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFragments = new BaseMvpFragment[1];
        if (savedInstanceState == null) {
            mFragments[FIRST] = new LoginFragment();
            loadRootFragment(getContainerId(), mFragments[FIRST]);
        } else {
            mFragments[FIRST] = findFragment(LoginFragment.class);
        }
    }

    public int getContainerId() {
        return R.id.fl_login;
    }
}
