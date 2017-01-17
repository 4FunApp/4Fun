package com.joker.fourfun.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.login.LoginContext;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.presenter.SplashPresenter;
import com.joker.fourfun.presenter.contract.SplashContract;
import com.joker.fourfun.utils.PreferenceUtil;

import butterknife.BindView;

public class SplashActivity extends BaseMvpActivity<SplashContract.View, SplashPresenter> implements
        SplashContract.View {
    public ScaleAnimation mAnimation;
    @BindView(R.id.iv_content)
    ImageView mContentImageView;
    private String mZhihuImg;
    private Picture mPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        mContentImageView = (ImageView) findViewById(R.id.iv_content);

        mPresenter.getZhihuPic();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 从 sharedPreference 获取登录状态
        boolean isLogin = PreferenceUtil.getDefaultFalse(Constants.LOGIN_STATE);
        LoginContext.getInstance().setState(isLogin);
    }

    @Override
    protected void setContentViewAndInject(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        getComponent().inject(this);
    }

    private void start2mainActivity() {
        // MediaFragment 背景图
        Bundle mediaBundle = new Bundle();
        mediaBundle.putString(Constants.ZHIHU_IMG, mZhihuImg);
        // PictureFragment 第一个 fragment 的数据
        Bundle pictureBundle = new Bundle();
        pictureBundle.putParcelable(Constants.PICTURE_ONE_IMG, mPicture);

        Bundle bundle = new Bundle();
        bundle.putBundle(Constants.MEDIA_BUNDLE, mediaBundle);
        bundle.putBundle(Constants.PICTURE_BUNDLE, pictureBundle);

        startActivity(MainActivity.newInstance(this, bundle));
        finish();
    }

    @Override
    public void showZhihuPic(String url) {
        Glide.with(this)
                .load(url)
                .animate(new ViewPropertyAnimation.Animator() {
                    @Override
                    public void animate(View view) {
                        mAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation
                                .RELATIVE_TO_SELF, 0.5f, Animation
                                .RELATIVE_TO_SELF, 0.5f);
                        mAnimation.setFillAfter(true);
                        mAnimation.setDuration(3000);
                        mAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                start2mainActivity();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                        view.startAnimation(mAnimation);
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .into(mContentImageView);
    }

    @Override
    public void setMediaBackground(String url) {
        this.mZhihuImg = url;
    }

    @Override
    public void setPictureOne(Picture picture) {
        this.mPicture = picture;
    }

    @Override
    public void showError(String message) {
        mZhihuImg = "";
        mAnimation.cancel();
//        SystemUtil.showToast(this, message);
        start2mainActivity();
    }
}
