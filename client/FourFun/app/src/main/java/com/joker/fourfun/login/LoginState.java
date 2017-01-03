package com.joker.fourfun.login;

import android.content.Context;

import com.joker.fourfun.login.contract.UserState;
import com.joker.fourfun.utils.SystemUtil;

/**
 * Created by joker on 2016/12/31.
 */

public class LoginState implements UserState {
    @Override
    public void collect(Context context) {
        SystemUtil.showToast(context, "已经登录，可以使用该功能");
    }

    @Override
    public void submit(Context context) {
        SystemUtil.showToast(context, "已经登录，可以使用该功能");
    }
}
