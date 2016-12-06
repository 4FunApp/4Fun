package com.joker.fourfun.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.presenter.SplashPresenter;
import com.joker.fourfun.presenter.contract.SplashContract;
import com.joker.fourfun.utils.SystemUtil;

import butterknife.BindView;

public class SplashActivity extends BaseMvpActivity<SplashContract.View, SplashPresenter> implements
        SplashContract.View {
    @BindView(R.id.iv_content)
    ImageView mContentImageView;
    private ScaleAnimation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        super.onCreate(savedInstanceState);
        mContentImageView = (ImageView) findViewById(R.id.iv_content);

        mPresenter.getZhihuPic();
        mAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation
                .RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
        mAnimation.setFillAfter(true);
        mAnimation.setDuration(3500);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    protected void initLayoutAndInject() {
        setContentView(R.layout.activity_splash);
        getComponent().inject(this);
    }

    @Override
    public void showZhihuPic(String url) {
        Glide.with(this)
                .load(url)
                .animate(new ViewPropertyAnimation.Animator() {
                    @Override
                    public void animate(View view) {
                        view.startAnimation(mAnimation);
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop()
                .into(mContentImageView);
    }

    @Override
    public void showError(String message) {
        mAnimation.cancel();
        SystemUtil.showToast(this, message);
    }

    @Override
    protected void onDestroy() {
        SystemUtil.cancelToast();
        super.onDestroy();
    }
}
