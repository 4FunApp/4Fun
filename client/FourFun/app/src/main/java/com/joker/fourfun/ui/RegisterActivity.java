package com.joker.fourfun.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.cengalabs.flatui.views.FlatToggleButton;
import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.model.User;
import com.joker.fourfun.presenter.RegisterPresenter;
import com.joker.fourfun.presenter.contract.RegisterContract;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class RegisterActivity extends BaseMvpActivity<RegisterContract.View, RegisterPresenter>
        implements RegisterContract.View {
    public static final int REQUEST_CODE = 10;
    public static final int THE_MALE_SEX = 0;
    public static final int THE_FEMALE_SEX = 1;
    @BindView(R.id.bt_register)
    Button mBtRegister;
    @BindView(R.id.fab_login)
    FloatingActionButton mFabLogin;
    @BindView(R.id.cv_add)
    CardView mCvAdd;
    @BindView(R.id.ftb_sex)
    FlatToggleButton mFtbSex;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.et_register_username)
    MaterialEditText mEtRegisterUsername;
    @BindView(R.id.et_register_email)
    MaterialEditText mEtRegisterEmail;
    @BindView(R.id.et_register_password)
    MaterialEditText mEtRegisterPassword;
    @BindView(R.id.et_register_repeat_password)
    MaterialEditText mEtRegisterRepeatPassword;
    // 默认为男
    private int sex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showEnterAnimation();
        }
        mFtbSex.setChecked(true);

        mEtRegisterUsername.addValidator(new RegexpValidator(getString(R.string
                .username_error_message_tip), Constants.REGEXP_EMPTY));
        mEtRegisterPassword.addValidator(new RegexpValidator(getString(R.string.
                password_error_message_tip),
                Constants.REGEXP_EMPTY));
        mEtRegisterRepeatPassword.addValidator(new RegexpValidator(getString(R.string.
                repeat_error_message_tip),
                Constants.REGEXP_EMPTY));
        mEtRegisterEmail.addValidator(new RegexpValidator(getString(R.string.
                email_error_message_tip),
                Constants.REGEXP_EMAIL));
    }

    @Override
    protected void setContentViewAndInject(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        getComponent().inject(this);
    }

    @Override
    public void registerSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void autoLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // 同时 finish 自己
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showDialog(final LoginInfo info) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title(getString(R.string.auto_login_tip))
                .content(getString(R.string.register_success_auto_login))
                .positiveText(getString(R.string.yes))
                .negativeText(getString(R.string.no))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.setLogin(info);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.cancelLogin(getString(R.string.cancel_login));
                    }
                });
        MaterialDialog dialog = builder.build();
        dialog.show();
    }

    @Override
    public void cancelLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition
                .fab_transition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                mCvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCvAdd, mCvAdd.getWidth() / 2, 0,
                mFabLogin
                        .getWidth() / 2, mCvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mCvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(mCvAdd, mCvAdd.getWidth() / 2, 0,
                mCvAdd.getHeight(), mFabLogin.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                mFabLogin.setImageResource(R.drawable.jelly_fish);
                RegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

    @OnClick({R.id.bt_register, R.id.fab_login, R.id.ftb_sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                boolean usernameValidated = mEtRegisterUsername.validate();
                boolean emailValidated = mEtRegisterEmail.validate();
                boolean passwordValidated = mEtRegisterPassword.validate();
                boolean repeatPasswordValidated = mEtRegisterRepeatPassword.validate();
                String password = mEtRegisterPassword.getText().toString().trim();
                String repeatPassword = mEtRegisterRepeatPassword.getText().toString().trim();
                boolean mIsPasswordEquals = repeatPassword.equals(password);
                if (!mIsPasswordEquals) {
                    mEtRegisterRepeatPassword.setError(getString(R.string.repeat_error_message_tip));
                }
                if (usernameValidated && emailValidated && passwordValidated &&
                        mIsPasswordEquals && repeatPasswordValidated) {
                    // 注册
                    User user = new User();
                    user.setSex(sex);
                    user.setEmail(mEtRegisterEmail.getText().toString().trim());
                    user.setPassword(mEtRegisterPassword.getText().toString().trim());
                    user.setUsername(mEtRegisterUsername.getText().toString().trim());
                    mPresenter.register(user);
                }
                break;
            case R.id.fab_login:
                animateRevealClose();
                break;
            case R.id.ftb_sex:
                boolean checked = mFtbSex.isChecked();
                if (checked) {
                    sex = THE_MALE_SEX;
                    mTvSex.setText(getString(R.string.women_should_not_select));
                } else {
                    sex = THE_FEMALE_SEX;
                    mTvSex.setText(getString(R.string.man_should_select));
                }
                break;
            default:
                break;
        }
    }

    @OnTextChanged(value = {R.id.et_register_username, R.id.et_register_email, R.id
            .et_register_password}, callback = OnTextChanged
            .Callback.BEFORE_TEXT_CHANGED)
    void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mEtRegisterUsername.validate();
        mEtRegisterEmail.validate();
        mEtRegisterPassword.validate();
    }

    @OnTextChanged(value = {R.id.et_register_username, R.id.et_register_email, R.id
            .et_register_password}, callback = OnTextChanged
            .Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        mEtRegisterUsername.validate();
        mEtRegisterEmail.validate();
        mEtRegisterPassword.validate();
    }

    @OnTextChanged(value = {R.id.et_register_username, R.id.et_register_email, R.id
            .et_register_password}, callback = OnTextChanged
            .Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable s) {
        mEtRegisterUsername.validate();
        mEtRegisterEmail.validate();
        mEtRegisterPassword.validate();
    }

    @OnFocusChange(value = {R.id.et_register_username, R.id.et_register_email, R.id
            .et_register_password})
    void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            switch (v.getId()) {
                case R.id.et_register_username:
                    mEtRegisterUsername.validate();
                    break;
                case R.id.et_register_email:
                    mEtRegisterEmail.validate();
                    break;
                case R.id.et_register_password:
                    mEtRegisterPassword.validate();
                    break;
                case R.id.et_register_repeat_password:
                    String password = mEtRegisterPassword.getText().toString().trim();
                    String repeatPassword = mEtRegisterRepeatPassword.getText().toString().trim();
                    boolean mIsPasswordEquals = repeatPassword.equals(password);
                    if (!mIsPasswordEquals) {
                        mEtRegisterRepeatPassword.setError(getString(R.string.repeat_error_message_tip));
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
