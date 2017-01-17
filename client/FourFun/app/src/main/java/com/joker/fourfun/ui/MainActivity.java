package com.joker.fourfun.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.login.LoginContext;
import com.joker.fourfun.login.LoginState;
import com.joker.fourfun.ui.fragment.MediaFragment;
import com.joker.fourfun.ui.fragment.MineFragment;
import com.joker.fourfun.ui.fragment.PictureFragment;
import com.joker.fourfun.ui.fragment.ReadFragment;
import com.joker.fourfun.ui.mode.ThemeModeContext;
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
    private int mDefaultUIFlag;
    private View mDecorView;
    private BaseMvpFragment[] mFragments;

    public static Intent newInstance(AppCompatActivity activity, Bundle bundle) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(Constants.MAIN_ACTIVITY_BUNDLE, bundle);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new Explode());
        }
        mDecorView = getWindow().getDecorView();
        mDefaultUIFlag = mDecorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_background));
//        setAppTheme();
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        analyseIntent();
        initChildViews(savedInstanceState);
    }

    private void setAppTheme() {
        ThemeModeContext.getInstance().setAppTheme(this);
    }

    private void initChildViews(Bundle savedInstanceState) {
        String[] texts = {getString(R.string.personal_center), getString(R.string.setting), getString(R
                .string
                .collection), getString(R.string.submit)};
        // 蓝，红，绿，黄
        int[] colors = {resId2color(R.color.blue_boom_menu_btn), resId2color(R.color.red_boom_menu_btn),
                resId2color(R.color.green_boom_menu_btn), resId2color(R.color.yellow_boom_menu_btn)};
        initBoomMenu(texts, colors);
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

    private void initBoomMenu(String[] texts, int[] colors) {
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
                                    startActivityForResult(new Intent(MainActivity.this, LoginActivity
                                            .class), LoginActivity.REQUEST_CODE);
                                    break;
                                case 1:
                                    showFragment(FOURTH);
                                    break;
                                case 2:
                                    LoginContext.getInstance().collect(MainActivity.this);
                                    break;
                                case 3:
                                    LoginContext.getInstance().submit(MainActivity.this);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });

            mBmbContent.addBuilder(builder);
        }
    }

    private void analyseIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.MAIN_ACTIVITY_BUNDLE)) {
            Bundle bundle = intent.getBundleExtra(Constants.MAIN_ACTIVITY_BUNDLE);
            mMediaBundle = bundle.getBundle(Constants.MEDIA_BUNDLE);
            mPictureBundle = bundle.getBundle(Constants.PICTURE_BUNDLE);
        }
    }

    protected int getContainerId() {
        return R.id.fl_content;
    }

    @OnClick({R.id.rb_pic, R.id.rb_article, R.id.rb_music, R.id.rb_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_pic:
                setImmerseMode(false);
                showFragment(FIRST);
                break;
            case R.id.rb_article:
                showFragment(SECOND);
                break;
            case R.id.rb_music:
                setImmerseMode(true);
                showFragment(THIRD);
                break;
            case R.id.rb_movie:
                setImmerseMode(true);
                showFragment(FOURTH);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LoginActivity.REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    // 登录成功
                }
                break;
            default:
                break;
        }
    }

    private void showFragment(int position) {
        if (position != showingPosition) {
            showHideFragment(mFragments[position], mFragments[showingPosition]);
            showingPosition = position;
        }
    }

    private int resId2color(int resId) {
        return ContextCompat.getColor(this, resId);
    }

    public void setImmerseMode(boolean setImmerse) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (setImmerse) {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                mDecorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                mDecorView.setSystemUiVisibility(mDefaultUIFlag);
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.main_background));
            }
        }
    }
}