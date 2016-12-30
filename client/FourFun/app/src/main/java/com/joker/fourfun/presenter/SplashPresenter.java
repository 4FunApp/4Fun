package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.model.Zhihu;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.SplashContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


/**
 * Created by joker on 2016/11/27.
 */

public class SplashPresenter extends BaseMvpPresenter<SplashContract.View> implements SplashContract
        .Presenter {
    private UserService mService;

    @Inject
    SplashPresenter(RetrofitUtil retrofitUtil) {
        mService = retrofitUtil.create(UserService.class);
    }

    @Override
    public void getZhihuPic() {
        mService
                .zhihuPic()
                .map(new HttpResultFun<List<Zhihu>>())
                .compose(RxUtil.<List<Zhihu>>rxSchedulerTransformer())
                .compose(RxUtil.<List<Zhihu>>rxTimeoutTransformer())
                .subscribe(new Subscriber<List<Zhihu>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<Zhihu> zhihu) {
                        mView.showZhihuPic(zhihu.get(0).getImg());
                        mView.setMediaBackground(zhihu.get(0).getImg());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t, t.getMessage());
                        mView.showError("似乎有点问题哦");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getPictureOne(int before) {
        String date = SystemUtil.beforeToday(before);
        mService.picOne(date)
                .map(new HttpResultFun<List<Picture>>())
                .compose(RxUtil.<List<Picture>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<Picture>>() {
                    @Override
                    public void accept(List<Picture> list) throws Exception {
                        mView.setPictureOne(list.get(0));
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
