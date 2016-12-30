package com.joker.fourfun.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.ui.fragment.MediaFragment;
import com.joker.fourfun.ui.fragment.MineFragment;
import com.joker.fourfun.ui.fragment.PictureFragment;
import com.joker.fourfun.ui.fragment.ReadFragment;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public Bundle mPictureBundle;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.rb_pic)
    RadioButton mRbPic;
    @BindView(R.id.rb_article)
    RadioButton mRbArticle;
    @BindView(R.id.rb_music)
    RadioButton mRbMusic;
    @BindView(R.id.rb_movie)
    RadioButton mRbMovie;
    @BindView(R.id.bmb_content)
    BoomMenuButton mBmbContent;
    private Bundle mMediaBundle;
    private int showingPosition = FIRST;
    private BaseMvpFragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        analyseIntent();
        initChildViews(savedInstanceState);
    }

    private void initChildViews(Bundle savedInstanceState) {
        String[] texts = {getString(R.string.login_register), getString(R.string.setting), getString(R
                .string
                .collection), getString(R.string.submit)};
        // 蓝，红，绿，黄
        int[] colors = {Color.rgb(0, 188, 212), Color.rgb(255, 205, 210), Color.rgb(144, 238, 144), Color
                .rgb(255, 249, 196)};
        for (int i = 0; i < mBmbContent.getButtonPlaceEnum().buttonNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(R.drawable.jelly_fish)
                    .normalText(texts[i])
                    .normalColor(colors[i])
                    .textGravity(Gravity.CENTER)
                    .rotateImage(true)
                    .shadowEffect(true)
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                default:
                                    break;
                            }
                        }
                    });

            mBmbContent.addBuilder(builder);
        }
        mFragments = new BaseMvpFragment[4];
        mRbPic.setChecked(true);
        if (savedInstanceState == null) {
            mFragments[FIRST] = PictureFragment.newInstance(mPictureBundle);
            mFragments[SECOND] = new ReadFragment();
            mFragments[THIRD] = MediaFragment.newInstance(mMediaBundle);
            mFragments[FOURTH] = new MineFragment();
            loadMultipleRootFragment(getContainerId(), FIRST, mFragments[FIRST], mFragments[SECOND],
                    mFragments[THIRD], mFragments[FOURTH]);
        } else {
            mFragments[FIRST] = findFragment(PictureFragment.class);
            mFragments[SECOND] = findFragment(ReadFragment.class);
            mFragments[THIRD] = findFragment(MediaFragment.class);
            mFragments[FOURTH] = findFragment(MineFragment.class);
        }
    }

    private void analyseIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constants.MAIN_ACTIVITY_BUNDLE);
        mMediaBundle = bundle.getBundle(Constants.MEDIA_BUNDLE);
        mPictureBundle = bundle.getBundle(Constants.PICTURE_BUNDLE);
    }

    protected int getContainerId() {
        return R.id.fl_content;
    }

    @OnClick({R.id.rb_pic, R.id.rb_article, R.id.rb_music, R.id.rb_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_pic:
                showFragment(FIRST);
                break;
            case R.id.rb_article:
                showFragment(SECOND);
                break;
            case R.id.rb_music:
                showFragment(THIRD);
                break;
            case R.id.rb_movie:
                showFragment(FOURTH);
                break;
        }
    }

    private void showFragment(int position) {
        if (position != showingPosition) {
            showHideFragment(mFragments[position], mFragments[showingPosition]);
            showingPosition = position;
        }
    }
}