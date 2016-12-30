package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.Picture;

/**
 * Created by joker on 2016/12/29.
 */

public interface PictureDetailContract {
    interface View extends BaseView {
        void showContent(Picture picture);
    }

    interface Presenter extends BasePresenter<View> {
        void getPicture(Picture picture, int position);
    }
}
