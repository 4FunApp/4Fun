package com.joker.fourfun.ui;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseFragmentActivity;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseFragmentActivity {
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    private RadioButton mRbPicinclude;
    private RadioButton mRbArticleinclude;
    private RadioButton mRbMusicinclude;
    private RadioButton mRbMovieinclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRbPicinclude = (RadioButton) findViewById(R.id.rb_pic);
        mRbArticleinclude = (RadioButton) findViewById(R.id.rb_article);
        mRbMusicinclude = (RadioButton) findViewById(R.id.rb_music);
        mRbMovieinclude = (RadioButton) findViewById(R.id.rb_movie);
    }

    @Override
    protected SupportFragment getToFragment() {
        return null;
    }

    @Override
    protected int getContainerId() {
        return R.id.fl_content;
    }
}
