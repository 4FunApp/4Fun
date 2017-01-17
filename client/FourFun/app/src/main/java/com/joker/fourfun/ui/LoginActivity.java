package com.joker.fourfun.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.login.LoginContext;
import com.joker.fourfun.login.LoginState;
import com.joker.fourfun.presenter.LoginPresenter;
import com.joker.fourfun.presenter.contract.LoginContract;
import com.joker.fourfun.utils.PreferenceUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class LoginActivity extends BaseMvpActivity<LoginContract.View, LoginPresenter> implements
        LoginContract.View {
    public static final int REQUEST_CODE = 10;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.fab_register)
    FloatingActionButton mFabLogin;
    @BindView(R.id.et_login_username)
    MaterialEditText mEtUsername;
    @BindView(R.id.et_login_password)
    MaterialEditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 不能为空
        mEtUsername.addValidator(new RegexpValidator(getString(R.string.username_error_message_tip),
                Constants.REGEXP_EMPTY));
        mEtPassword.addValidator(new RegexpValidator(getString(R.string.password_error_message_tip),
                Constants.REGEXP_EMPTY));
    }

    @Override
    protected void setContentViewAndInject(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        getComponent().inject(this);
    }

    @Override
    public void loginSuccess(String message) {
        SystemUtil.showToast(this, message);
        LoginContext.getInstance().setState(new LoginState());
        PreferenceUtil.putBoolean(Constants.LOGIN_STATE, true);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void usernameEmpty() {
        mEtUsername.setError(getString(R.string.username_error_message_tip));
    }

    @Override
    public void passwordEmpty() {
        mEtPassword.setError(getString(R.string.password_error_message_tip));
    }

    @Override
    public void showError(String message) {
        SystemUtil.showToast(this, message);
    }

    @OnClick({R.id.bt_login, R.id.fab_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                String username = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                mPresenter.loginUser(username, password);
                break;
            case R.id.fab_register:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);

                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, mFabLogin, mFabLogin
                                    .getTransitionName
                                            ());
                    startActivityForResult(new Intent(this, RegisterActivity.class), RegisterActivity
                            .REQUEST_CODE, options.toBundle());
                } else {
                    startActivityForResult(new Intent(this, RegisterActivity.class), RegisterActivity
                            .REQUEST_CODE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RegisterActivity.REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    loginSuccess(Constants.LOGIN_SUCCESS_MESSAGE);
                }
                break;
            default:
                break;
        }
    }

    @OnTextChanged(value = {R.id.et_login_username, R.id.et_login_password}, callback = OnTextChanged
            .Callback.BEFORE_TEXT_CHANGED)
    void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mEtUsername.validate();
        mEtPassword.validate();
    }

    @OnTextChanged(value = {R.id.et_login_username, R.id.et_login_password}, callback = OnTextChanged
            .Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        mEtUsername.validate();
        mEtPassword.validate();
    }

    @OnTextChanged(value = {R.id.et_login_username, R.id.et_login_password}, callback = OnTextChanged
            .Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s) {
        mEtUsername.validate();
        mEtPassword.validate();
    }

    @OnFocusChange(value = {R.id.et_login_username, R.id.et_login_password})
    void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_login_username:
                mEtUsername.validate();
                break;
            case R.id.et_login_password:
                mEtPassword.validate();
                break;
            default:
                mEtUsername.validate();
                mEtPassword.validate();
                break;
        }
    }
}
