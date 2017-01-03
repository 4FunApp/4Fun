package com.joker.fourfun.login;

import android.content.Context;
import android.content.Intent;

import com.joker.fourfun.login.contract.UserState;
import com.joker.fourfun.ui.LoginActivity;
import com.joker.fourfun.utils.SystemUtil;

/**
 * Created by joker on 2016/12/31.
 */

public class LogoutState implements UserState {
    @Override
    public void collect(Context context) {
        start2loginActivity(context);
    }

    @Override
    public void submit(Context context) {
        start2loginActivity(context);
    }

    private void start2loginActivity(Context context) {
        SystemUtil.showToast(context, "请先登录哦");
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
