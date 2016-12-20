package com.joker.fourfun.ui.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.adapter.MovieAdapter;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;
import com.joker.fourfun.presenter.MediaPresenter;
import com.joker.fourfun.presenter.contract.MediaContract;
import com.joker.fourfun.utils.GlideUtil;
import com.joker.fourfun.utils.SystemUtil;
import com.joker.fourfun.view.DividerItemDecoration;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaFragment extends BaseMvpFragment<MediaContract.View, MediaPresenter> implements
        MediaContract.View {
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.scrolling_header)
    ImageView mIvHeader;
    @BindView(R.id.tv_song)
    TextView mTvSong;
    @BindView(R.id.tv_singer)
    TextView mTvSinger;
    @BindView(R.id.iv_singer)
    ImageView mIvSinger;
    @BindView(R.id.btn_play)
    Button mBtnPlay;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    private String mZhihuImage;
    private MediaPlayer mPlayer;
    private boolean isPlaying = false;

    public static MediaFragment newInstance(Bundle bundle) {
        MediaFragment fragment = new MediaFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        ButterKnife.bind(this, view);
        mRvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvContent.addItemDecoration(new DividerItemDecoration(25));

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        analyseArguments();
        GlideUtil.setImage(mActivity, mZhihuImage, mIvHeader);
        mPresenter.getMovie(SystemUtil.beforeToday(0));
        mPresenter.getMusic(SystemUtil.beforeToday(0));
    }

    private void analyseArguments() {
        Bundle bundle = getArguments();
        mZhihuImage = bundle.getString(Constants.ZHIHU_IMG, "");
    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }

    @Override
    public void showMusic(Music music) {
        GlideUtil.setCirCleImage(mActivity, music.getImgUrl(), mIvSinger);
        mTvSinger.setText(music.getArtistName());
        mTvSong.setText(music.getSongName());
        mTvDate.setText(music.getDate());
        String songLink = music.getSongLink();
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(songLink);
            mPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMovie(List<Movie> movieList) {
        mRvContent.setAdapter(new MovieAdapter(mActivity, movieList));
    }

    @Override
    public void showError(String message) {

    }

    @OnClick(R.id.btn_play)
    public void onClick() {
        if (!isPlaying) {
            Toast.makeText(mActivity, "播放", Toast.LENGTH_SHORT).show();
            mPlayer.start();
            mBtnPlay.setBackgroundResource(R.drawable.music_pressed);
        } else {
            mPlayer.stop();
            mBtnPlay.setBackgroundResource(R.drawable.music_normal);
        }
        isPlaying = !isPlaying;
    }
}
