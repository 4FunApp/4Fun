package com.joker.fourfun.presenter.contract;

import com.joker.fourfun.base.BasePresenter;
import com.joker.fourfun.base.BaseView;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;

import java.util.List;

/**
 * Created by joker on 2016/12/18.
 */

public interface MediaContract {
    interface View extends BaseView {
        void pictureBackground(String imgUrl);

        void showMusic(Music music);

        void showMovie(List<Movie> movieList);
    }

    interface Presenter extends BasePresenter<View> {
        void getPicture(String imgUrl);

        void getMusic(String date);

        void getMovie(String date);

        void downloadMusic();
    }
}
