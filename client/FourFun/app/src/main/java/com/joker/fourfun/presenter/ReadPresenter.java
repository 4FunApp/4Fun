package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.ArticleOne;
import com.joker.fourfun.net.HttpResultFunc;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.ReadContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by joker on 2016/12/8.
 */

public class ReadPresenter extends BaseMvpPresenter<ReadContract.View> implements ReadContract.Presenter {
    private UserService mService;

    @Inject
    ReadPresenter(RetrofitUtil retrofit) {
        mService = retrofit.create(UserService.class);
    }

    @Override
    public void getArticle(String date) {
        Disposable subscribe = mService.article(date)
                .onBackpressureLatest()
                .map(new HttpResultFunc<List<ArticleOne>>())
                .compose(RxUtil.<List<ArticleOne>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<ArticleOne>>() {
                    @Override
                    public void accept(List<ArticleOne> list) throws Exception {
                        ArticleOne article = list.get(0);
                        mView.showArticle(article);
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
}
