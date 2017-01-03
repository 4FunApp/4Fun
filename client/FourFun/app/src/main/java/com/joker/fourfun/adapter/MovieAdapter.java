package com.joker.fourfun.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.fourfun.R;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.utils.GlideUtil;
import com.joker.fourfun.utils.SystemUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by joker on 2016/12/19.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context mContext;
    private List<Movie> list;
    private OnClickedListener listener;

    public MovieAdapter(Context context, List<Movie> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(mContext).inflate(R.layout
                .fragment_media_movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
        String movieName = list.get(position).getMovieName();
        String briefIntro = list.get(position).getBriefIntro();

        String content = movieName + "\n\n" + briefIntro;
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dp2px(20)), 0, movieName.length(),
                SPAN_INCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(SystemUtil.dp2px(14)), movieName.length() + 2, content.length
                () - 1, SPAN_INCLUSIVE_INCLUSIVE);
        holder.mTvContent.setText(style);

        GlideUtil.setImage(mContext, list.get(position).getPic(), holder.mIvMovie);
        holder.mCvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.click(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(OnClickedListener listener) {
        this.listener = listener;
    }

    public interface OnClickedListener {
        void click(Movie movie);
    }

    static class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.iv_movie)
        ImageView mIvMovie;
        @BindView(R.id.cv_movie)
        CardView mCvMovie;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}