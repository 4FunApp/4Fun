package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;

/**
 * Created by joker on 2016/11/30.
 */

public interface MainContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
