package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.PictureChildContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by joker on 2016/12/1.
 */

public class PictureChildPresenter extends BaseMvpPresenter<PictureChildContract.View> implements
        PictureChildContract
                .Presenter {
    private RetrofitUtil mRetrofit;

    @Inject
    PictureChildPresenter(RetrofitUtil retrofitUtil) {
        mRetrofit = retrofitUtil;
    }

    @Override
    public void getContent(int before) {
        String date = SystemUtil.beforeToday(before);
        UserService service = mRetrofit.create(UserService.class);
        service.picOne(date)
                .map(new HttpResultFun<List<Picture>>())
                .compose(RxUtil.<List<Picture>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<Picture>>() {
                    @Override
                    public void accept(List<Picture> list) throws Exception {
                        Logger.e(list.get(0).getPicUrl());
                        mView.showContent(list.get(0));
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
