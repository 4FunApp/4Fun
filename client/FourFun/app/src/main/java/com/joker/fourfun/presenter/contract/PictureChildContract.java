package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.PicOne;

/**
 * Created by joker on 2016/12/1.
 */

public interface PictureChildContract {
    interface View extends BaseView {
        void showContent(PicOne picOne);
    }

    interface Presenter extends BasePresenter<View> {
        void getContent(int before);
    }
}
