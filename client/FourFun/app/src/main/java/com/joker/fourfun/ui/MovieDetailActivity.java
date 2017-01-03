package com.joker.fourfun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.fourfun.Constants;
import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpActivity;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.presenter.MovieDetailPresenter;
import com.joker.fourfun.presenter.contract.MovieDetailContract;
import com.joker.fourfun.utils.SystemUtil;

import butterknife.BindView;

public class MovieDetailActivity extends BaseMvpActivity<MovieDetailContract.View, MovieDetailPresenter>
        implements MovieDetailContract.View {
    @BindView(R.id.tv_movie_content)
    TextView mTvMovieContent;
    private Movie mMovie;

    public static Intent newInstance(Activity activity, Movie movie) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.MOVIE_DETAILS_BEAN, movie);
        intent.putExtra(Constants.MOVIE_BUNDLE, bundle);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        analyseIntent();
        String day = SystemUtil.beforeToday(0);
        mPresenter.getMovie(mMovie, day);
    }

    private void analyseIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.MOVIE_BUNDLE)) {
            Bundle bundle = intent.getBundleExtra(Constants.MOVIE_BUNDLE);
            mMovie = bundle.getParcelable(Constants.MOVIE_DETAILS_BEAN);
        }
    }

    @Override
    protected void setContentViewAndInject(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie_detail);
        getComponent().inject(this);
    }

    @Override
    public void showContent(Movie movie) {
        String movieName = movie.getMovieName();
        String country = movie.getCountry();
        String briefIntro = movie.getBriefIntro();

        String content = movieName + "\n" + country + "\n" + briefIntro;
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dp2px(30)), 0, movieName.length(), Spanned
                .SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dp2px(20)), movieName.length() + 1, movieName
                .length() + 1 + country.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, movieName
                .length() + 1 + country.length(), Spanned
                .SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dp2px(16)), movieName.length() + country
                .length() + 3, content.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        mTvMovieContent.setText(style);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
