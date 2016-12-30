package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.net.HttpResultFun;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.LoginContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by joker on 2016/12/30.
 */

public class LoginPresenter extends BaseMvpPresenter<LoginContract.View> implements LoginContract
        .Presenter {
    private UserService mService;

    @Inject
    LoginPresenter(RetrofitUtil retrofitUtil) {
        mService = retrofitUtil.create(UserService.class);
    }

    @Override
    public void loginUser(Map<String, String> userInfo) {
        mService.login(userInfo)
                .map(new HttpResultFun<List<LoginInfo>>())
                .compose(RxUtil.<List<LoginInfo>>rxSchedulerTransformer())
                .subscribe(new Consumer<List<LoginInfo>>() {
                    @Override
                    public void accept(List<LoginInfo> loginInfo) throws Exception {
                        mView.showMsg(loginInfo.get(0).getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable, throwable.getMessage());
                        mView.showError("似乎有点问题哦");
                    }
                });
    }
}
