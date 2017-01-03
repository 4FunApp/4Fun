package com.joker.fourfun.presenter;

import com.joker.fourfun.Constants;
import com.joker.fourfun.base.BaseMvpPresenter;
import com.joker.fourfun.login.LoginContext;
import com.joker.fourfun.login.LoginState;
import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.net.HttpResultFunc;
import com.joker.fourfun.net.UserService;
import com.joker.fourfun.presenter.contract.LoginContract;
import com.joker.fourfun.utils.RetrofitUtil;
import com.joker.fourfun.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.joker.fourfun.Constants.LOGIN_SUCCESS_CODE;

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
    public void loginUser(String username, String password) {
        if (username.isEmpty()) {
            usernameError();
        }
        if (password.isEmpty()) {
            passwordError();
        }
        if (!username.isEmpty() && !password.isEmpty()) {
            Disposable subscribe = mService.login(username, password)
                    .map(new HttpResultFunc<List<LoginInfo>>())
                    .compose(RxUtil.rxStateCheck(LOGIN_SUCCESS_CODE))
                    .compose(RxUtil.<LoginInfo>rxSchedulerTransformer())
                    .subscribe(new Consumer<LoginInfo>() {
                        @Override
                        public void accept(LoginInfo info) throws Exception {
                            if (info.getCode() == LOGIN_SUCCESS_CODE) {
                                mView.loginSuccess(Constants.LOGIN_SUCCESS_MESSAGE);
                                LoginContext.getInstance().setState(new LoginState());
                            }
                            Logger.e(String.valueOf(info.getCode()));
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Logger.e(throwable, throwable.getMessage());
                            mView.showError(throwable.getMessage());
                        }
                    });
            addSubscription(subscribe);
        }
    }

    @Override
    public void usernameError() {
        mView.usernameEmpty();
    }

    @Override
    public void passwordError() {
        mView.passwordEmpty();
    }
}
