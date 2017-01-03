package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.Movie;

/**
 * Created by joker on 2017/1/3.
 */

public interface MovieDetailContract {
    interface View extends BaseView {
        void showContent(Movie movie);
    }

    interface Presenter extends BasePresenter<View> {
        void getMovie(Movie movie, String date);
    }
}
