package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.net.HttpResultFunc;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.PictureDetailContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.joker.fourfun.utils.SystemUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by joker on 2016/12/29.
 */

public class PictureDetailPresenter extends BaseMvpPresenter<PictureDetailContract.View> implements
        PictureDetailContract.Presenter {
    private UserService mService;

    @Inject
    PictureDetailPresenter(RetrofitUtil retrofitUtil) {
        mService = retrofitUtil.create(UserService.class);
    }

    @Override
    public void getPicture(Picture picture, final int position) {
        if (picture != null) {
            mView.showContent(picture);
        } else {
            Disposable subscribe = mService.picOne(SystemUtil.beforeToday(-1))
                    .map(new HttpResultFunc<List<Picture>>())
                    .compose(RxUtil.<List<Picture>>rxSchedulerTransformer())
                    .map(new Function<List<Picture>, Picture>() {
                        @Override
                        public Picture apply(List<Picture> pictures) throws Exception {
                            return pictures.get(position);
                        }
                    })
                    .subscribe(new Consumer<Picture>() {
                        @Override
                        public void accept(Picture picture) throws Exception {
                            mView.showContent(picture);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mView.showError(throwable.getMessage());
                        }
                    });
            addSubscription(subscribe);
        }
    }
}
