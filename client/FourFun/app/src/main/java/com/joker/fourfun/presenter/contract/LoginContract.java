package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;

import java.util.Map;

/**
 * Created by joker on 2016/12/30.
 */

public interface LoginContract {
    interface View extends BaseView {
        void showMsg(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void loginUser(Map<String, String> userInfo);
    }
}
