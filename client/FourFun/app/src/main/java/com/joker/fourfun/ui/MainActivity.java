package com.joker.fourfun.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.ui.fragment.PictureFragment;
import com.joker.fourfun.ui.fragment.ReadFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
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
    private int showingPosition = FIRST;
    private BaseMvpFragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        mFragments = new BaseMvpFragment[3];
        mRbPic.setChecked(true);
        if (savedInstanceState == null) {
            mFragments[FIRST] = new PictureFragment();
            mFragments[SECOND] = new ReadFragment();
            loadMultipleRootFragment(getContainerId(), FIRST, mFragments[FIRST], mFragments[SECOND]);
        } else {
            mFragments[FIRST] = findFragment(PictureFragment.class);
            mFragments[SECOND] = findFragment(ReadFragment.class);
        }
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
                break;
            case R.id.rb_movie:
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