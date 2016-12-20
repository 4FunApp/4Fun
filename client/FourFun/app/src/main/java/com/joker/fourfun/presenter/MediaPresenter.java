package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.MediaContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by joker on 2016/12/18.
 */

public class MediaPresenter extends BaseMvpPresenter<MediaContract.View> implements MediaContract
        .Presenter {
    private RetrofitUtil mRetrofit;

    @Inject
    MediaPresenter(RetrofitUtil retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public void getMusic(String date) {
        UserService service = mRetrofit.create(UserService.class);
        service.music(date)
//                .map(new HttpResultFun<List<Music>>())
                .map(new HttpResultFun<List<Music>>())
                .compose(RxUtil.<List<Music>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<Music>>() {
                    @Override
                    public void accept(List<Music> list) throws Exception {
                        Music music = list.get(0);
                        mView.showMusic(music);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable, "ERROR");
                        mView.showError("似乎有点问题哦");
                    }
                });
    }

    @Override
    public void getMovie(String date) {
        UserService service = mRetrofit.create(UserService.class);
        service.movie(date)
                .map(new HttpResultFun<List<Movie>>())
                .compose(RxUtil.<List<Movie>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(List<Movie> list) throws Exception {
                        mView.showMovie(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable, "ERROR");
                        mView.showError("似乎有点问题哦");
                    }
                });
    }
}
