package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.PicOne;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.PictureChildContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joker on 2016/12/1.
 */

public class PictureChildPresenter extends BaseMvpPresenter<PictureChildContract.View> implements
        PictureChildContract
        .Presenter {
    private RetrofitUtil mRetrofit;

    @Inject
    public PictureChildPresenter(RetrofitUtil retrofitUtil) {
        mRetrofit = retrofitUtil;
    }

    @Override
    public void getContent(int before) {
        String date = SystemUtil.beforeToday(before);
        UserService service = mRetrofit.create(UserService.class);
        service.picOne(date)
                .map(new HttpResultFun<List<PicOne>>())
                .compose(RxUtil.<List<PicOne>>rxSchedulerTransformer())
                .subscribe(new Subscriber<List<PicOne>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<PicOne> picOnes) {
                        Logger.e(picOnes.get(0).getPicUrl());
                        mView.showContent(picOnes.get(0));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t, "ERROR");
                        mView.showError("似乎有点问题哦");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
