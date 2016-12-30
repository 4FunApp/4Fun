package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.Picture;

/**
 * Created by joker on 2016/11/27.
 */

public interface SplashContract {
    interface View extends BaseView {
        void showZhihuPic(String url);

        void setMediaBackground(String url);

        void setPictureOne(Picture picture);
    }

    interface Presenter extends BasePresenter<View> {
        void getZhihuPic();

        void getPictureOne(int before);
    }
}
