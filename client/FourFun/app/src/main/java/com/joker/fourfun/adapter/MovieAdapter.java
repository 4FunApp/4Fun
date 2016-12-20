package com.joker.fourfun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.fourfun.R;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.utils.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by joker on 2016/12/19.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context mContext;
    private List<Movie> list;

    public MovieAdapter(Context context, List<Movie> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(mContext).inflate(R.layout
                .movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.mTvContent.setText(list.get(position).getMovieName());
        GlideUtil.setImage(mContext, list.get(position).getPic(), holder.mIvMovie);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView mTvContent;
        @BindView(R.id.iv_movie)
        ImageView mIvMovie;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}