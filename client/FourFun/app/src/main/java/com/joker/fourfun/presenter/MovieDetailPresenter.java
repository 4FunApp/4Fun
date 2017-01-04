package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.net.HttpResultFunc;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.MovieDetailContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by joker on 2017/1/3.
 */

public class MovieDetailPresenter extends BaseMvpPresenter<MovieDetailContract.View> implements
        MovieDetailContract.Presenter {
    private UserService mService;
    private Subscription scription;

    @Inject
    MovieDetailPresenter(RetrofitUtil retrofitUtil) {
        mService = retrofitUtil.create(UserService.class);
    }

    @Override
    public void getMovie(Movie movie, String date) {
        if (movie == null) {
            Disposable subscribe = mService.movie(date)
                    .onBackpressureLatest()
                    .map(new HttpResultFunc<List<Movie>>())
                    .compose(RxUtil.<List<Movie>>rxSchedulerTransformer())
                    .subscribe(new Consumer<List<Movie>>() {
                        @Override
                        public void accept(List<Movie> movieList) throws Exception {
                            mView.showContent(movieList.get(0));
                        }
                    });
            addSubscription(subscribe);
        } else {
            mView.showContent(movie);
        }
    }
}
