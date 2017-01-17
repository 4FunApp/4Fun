package com.joker.fourfun.login;

import android.content.Context;

import com.joker.fourfun.login.contract.UserState;

/**
 * 全局单例获取登录状态
 * Created by joker on 2016/12/31.
 */

public class LoginContext {
    private static LoginContext mLoginContext = null;
    UserState mState = new LogoutState();

    private LoginContext() {
    }

    public static LoginContext getInstance() {
        if (mLoginContext == null) {
            synchronized (LoginContext.class) {
                if (mLoginContext == null) {
                    mLoginContext = new LoginContext();
                }
            }
        }

        return mLoginContext;
    }

    public void setState(UserState state) {
        mState = state;
    }

    public void setState(boolean isLogin) {
        UserState userState = isLogin ? new LoginState() : new LogoutState();
        setState(userState);
    }

    // 点赞
    public void submit(Context context) {
        mState.submit(context);
    }

    // 收藏
    public void collect(Context context) {
        mState.collect(context);
    }
}
