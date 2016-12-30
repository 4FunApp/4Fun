package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.User;

/**
 * Created by joker on 2016/12/30.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void showMsg(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void register(User user);
    }
}
