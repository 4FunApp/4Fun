package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Zhihu;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.SplashContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by joker on 2016/11/27.
 */

public class SplashPresenter extends BaseMvpPresenter<SplashContract.View> implements SplashContract
        .Presenter {
    private RetrofitUtil mRetrofit;

    @Inject
    public SplashPresenter(RetrofitUtil retrofitUtil) {
        mRetrofit = retrofitUtil;
    }

    @Override
    public void getZhihuPic() {
        UserService userService = mRetrofit.create(UserService.class);
        userService
                .zhihuPic()
                .map(new HttpResultFun<List<Zhihu>>())
                .compose(RxUtil.<List<Zhihu>>rxSchedulerTransformer())
                .subscribe(new Subscriber<List<Zhihu>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<Zhihu> zhihu) {
                        mView.showZhihuPic(zhihu.get(0).getImg());
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
}
