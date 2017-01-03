package com.joker.fourfun.presenter;

import android.text.TextUtils;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;
import com.joker.fourfun.model.Zhihu;
import com.joker.fourfun.net.HttpResultFunc;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.MediaContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by joker on 2016/12/18.
 */

public class MediaPresenter extends BaseMvpPresenter<MediaContract.View> implements MediaContract
        .Presenter {
    private UserService mService;
    private String mSongLink;
    private String mSongName;

    @Inject
    MediaPresenter(RetrofitUtil retrofit) {
        mService = retrofit.create(UserService.class);
    }

    @Override
    public void getMusic(String date) {
        Disposable subscribe = mService.music(date)
                .map(new HttpResultFunc<List<Music>>())
                .compose(RxUtil.<List<Music>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<Music>>() {
                    @Override
                    public void accept(List<Music> list) throws Exception {
                        Music music = list.get(0);
                        mSongLink = music.getSongLink();
                        mSongName = music.getSongName();
                        mView.showMusic(music);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable, "ERROR");
                        mView.showError("似乎有点问题哦");
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void getMovie(String date) {
        Disposable subscribe = mService.movie(date)
                .map(new HttpResultFunc<List<Movie>>())
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
        addSubscription(subscribe);
    }

    @Override
    public void downloadMusic() {
        Disposable subscribe = mService.downloadMusic(mSongLink)
                .subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, InputStream>() {
                    @Override
                    public InputStream apply(ResponseBody responseBody) throws Exception {
                        return responseBody.byteStream();
                    }
                })
                .subscribe(new Consumer<InputStream>() {
                    @Override
                    public void accept(InputStream inputStream) throws Exception {
                        File file = new File(SystemUtil.getCacheFileDirPath(), mSongName);
                        SystemUtil.inputStream2file(file, inputStream);
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void getPicture(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            Disposable subscribe = mService.zhihuPic()
                    .map(new HttpResultFunc<List<Zhihu>>())
                    .compose(RxUtil.<List<Zhihu>>rxSchedulerTransformer())
                    .subscribe(new Consumer<List<Zhihu>>() {
                        @Override
                        public void accept(List<Zhihu> list) throws Exception {
                            mView.pictureBackground(list.get(0).getImg());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Logger.e(throwable, "ERROR");
                            mView.showError("似乎有点问题哦");
                        }
                    });
            addSubscription(subscribe);
        } else {
            mView.pictureBackground(imgUrl);
        }
    }
}