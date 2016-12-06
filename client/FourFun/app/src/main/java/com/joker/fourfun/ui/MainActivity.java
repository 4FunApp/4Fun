package com.joker.fourfun.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.ui.fragment.PictureFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        BaseMvpFragment[] mFragments = new BaseMvpFragment[2];
        if (savedInstanceState == null) {
            mFragments[0] = new PictureFragment();
            loadMultipleRootFragment(getContainerId(), 0, mFragments[0]);
        } else {
            mFragments[0] = findFragment(PictureFragment.class);
        }
    }

    protected int getContainerId() {
        return R.id.fl_content;
    }

    @OnClick({R.id.rb_pic, R.id.rb_article, R.id.rb_music, R.id.rb_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_pic:
                break;
            case R.id.rb_article:
                break;
            case R.id.rb_music:
                break;
            case R.id.rb_movie:
                break;
        }
    }
}
