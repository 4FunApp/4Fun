package com.joker.fourfun.presenter;

import android.text.TextUtils;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;
import com.joker.fourfun.model.Zhihu;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.MediaContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;

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
        mService.music(date)
                .map(new HttpResultFun<List<Music>>())
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
    }

    @Override
    public void getMovie(String date) {
        mService.movie(date)
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

    @Override
    public void downloadMusic() {
        mService.downloadMusic(mSongLink)
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
                        inputStream2file(file, inputStream);
                    }
                });
    }

    private void inputStream2file(File file, InputStream stream) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = stream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void getPicture(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            mService.zhihuPic()
                    .map(new HttpResultFun<List<Zhihu>>())
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
        } else {
            mView.pictureBackground(imgUrl);
        }
    }
}