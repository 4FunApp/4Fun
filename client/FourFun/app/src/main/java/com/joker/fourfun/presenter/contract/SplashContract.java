package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;

/**
 * Created by joker on 2016/11/27.
 */

public interface SplashContract {
    interface View extends BaseView {
        void showZhihuPic(String url);
    }

    interface Presenter extends BasePresenter<View> {
        void getZhihuPic();
    }
}
