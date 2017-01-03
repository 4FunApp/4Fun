package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.model.User;

/**
 * Created by joker on 2016/12/30.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void registerSuccess(String message);

        void autoLoginSuccess(String message);

        void showDialog(LoginInfo loginInfo);

        void cancelLogin(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void register(User user);

        void setLogin(LoginInfo info);

        void cancelLogin(String message);
    }
}
