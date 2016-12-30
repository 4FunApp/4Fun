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
import com.joker.fourfun.widget.DividerItemDecoration;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaFragment extends BaseMvpFragment<MediaContract.View, MediaPresenter> implements
        MediaContract.View, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer
        .OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {
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
    public String mSongName;

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
        mPresenter.getPicture(mZhihuImage);
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
    public void pictureBackground(String imgUrl) {
        GlideUtil.setImage(mActivity, imgUrl, mIvHeader);
    }

    @Override
    public void showMusic(Music music) {
        GlideUtil.setCirCleImage(mActivity, music.getImgUrl(), mIvSinger);
        mTvSinger.setText(music.getArtistName());
        mTvSong.setText(music.getSongName());
        mTvDate.setText(music.getDate());
        mSongName = music.getSongName();
        initPlayer();
    }

    private void initPlayer() {
        try {
            mPlayer = new MediaPlayer();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(SystemUtil.getCacheFileDirPath() + mSongName);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnBufferingUpdateListener(this);
            mPlayer.prepareAsync();
            // 下载
            mPresenter.downloadMusic();
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
            mPlayer.reset();
            mBtnPlay.setBackgroundResource(R.drawable.music_normal);
            initPlayer();
        }
        isPlaying = !isPlaying;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    /**
     * 当 MediaPlayer 完成播放音频文件时，将调用 onCompletion 方法
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        Logger.e("播放结束");
    }

    /**
     * 当 MediaPlayer 出现错误，将调用 onError 方法
     * @param mp
     * @param what
     * @param extra
     * @return
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(mActivity, "哎呀，播放器好像出问题了呢", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 当完成 prepareAsync 方法时，将调用 onPrepared 方法，表明音频准备播放
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        Logger.e("歌曲准备完毕");
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        Logger.e(String.valueOf(percent));
    }
}
