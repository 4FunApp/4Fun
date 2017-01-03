package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;

/**
 * Created by joker on 2016/12/30.
 */

public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess(String message);

        void usernameEmpty();

        void passwordEmpty();
    }

    interface Presenter extends BasePresenter<View> {
        void loginUser(String username, String password);

        void usernameError();

        void passwordError();
    }
}
