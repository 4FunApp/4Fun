package com.joker.fourfun.presenter;

import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.model.User;
import com.joker.fourfun.net.HttpResultFunc;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.RegisterContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.joker.fourfun.Constants.LOGIN_SUCCESS_CODE;
import static com.joker.fourfun.Constants.LOGIN_SUCCESS_MESSAGE;
import static com.joker.fourfun.Constants.REGISTER_SUCCESS_CODE;
import static com.joker.fourfun.Constants.REGISTER_SUCCESS_MESSAGE;

/**
 * Created by joker on 2016/12/30.
 */

public class RegisterPresenter extends BaseMvpPresenter<RegisterContract.View> implements
        RegisterContract.Presenter {
    private UserService mService;

    @Inject
    RegisterPresenter(RetrofitUtil retrofitUtil) {
        mService = retrofitUtil.create(UserService.class);
    }

    @Override
    public void register(User user) {
        Disposable subscribe = mService.register(user)
                .map(new HttpResultFunc<List<LoginInfo>>())
                .compose(RxUtil.rxStateCheck(REGISTER_SUCCESS_CODE))
                .compose(RxUtil.<LoginInfo>rxSchedulerTransformer())
                .subscribe(new Consumer<LoginInfo>() {
                    @Override
                    public void accept(LoginInfo loginInfo) throws Exception {
                        mView.registerSuccess(REGISTER_SUCCESS_MESSAGE);
                        mView.showDialog(loginInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                        Logger.e(throwable, throwable.getMessage());
                    }
                });
                /*
                // 回到 io 线程做登录请求
                .observeOn(Schedulers.io())
                .flatMap(new Function<LoginInfo, Flowable<HttpResult<List<LoginInfo>>>>() {
                    @Override
                    public Flowable<HttpResult<List<LoginInfo>>> apply(LoginInfo loginInfo) throws
                            Exception {
                        return mService.login(loginInfo.getUserName(), loginInfo.getPassword());
                    }
                })
                .map(new HttpResultFunc<List<LoginInfo>>())
                .compose(RxUtil.rxStateCheck(Constants.LOGIN_SUCCESS_CODE))
                // 网络请求结束，回到主线程
                .compose(RxUtil.<LoginInfo>rxSchedulerTransformer())
                .subscribe(new Consumer<LoginInfo>() {
                    @Override
                    public void accept(LoginInfo loginInfo) throws Exception {
                        mView.autoLoginSuccess(Constants.LOGIN_SUCCESS_MESSAGE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable, throwable.getMessage());
                        mView.showError(throwable.getMessage());
                    }
                });*/
        addSubscription(subscribe);
    }

    @Override
    public void setLogin(LoginInfo info) {
        Disposable subscribe = mService.login(info.getUserName(), info.getPassword())
                .map(new HttpResultFunc<List<LoginInfo>>())
                .compose(RxUtil.rxStateCheck(LOGIN_SUCCESS_CODE))
                .compose(RxUtil.<LoginInfo>rxSchedulerTransformer())
                .subscribe(new Consumer<LoginInfo>() {
                    @Override
                    public void accept(LoginInfo loginInfo) throws Exception {
                        mView.autoLoginSuccess(LOGIN_SUCCESS_MESSAGE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError(throwable.getMessage());
                        Logger.e(throwable, throwable.getMessage());
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void cancelLogin(String message) {
        mView.cancelLogin(message);
    }
}
