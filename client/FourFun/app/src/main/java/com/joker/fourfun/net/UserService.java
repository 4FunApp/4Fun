package com.joker.fourfun.net;

import com.joker.fourfun.model.ArticleOne;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.model.Zhihu;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by joker on 2016/11/27.
 */

public interface UserService {
    @GET("PicZhihuServlet")
    Flowable<HttpResult<List<Zhihu>>> zhihuPic();

    @GET("PicOneServlet")
    Flowable<HttpResult<List<Picture>>> picOne(@Query("date") String date);

    @GET("PicOneServlet")
    Flowable<HttpResult<List<Picture>>> picOneNum(@Query("num") String num);

    @GET("ArOneServlet")
    Flowable<HttpResult<List<ArticleOne>>> article(@Query("num") int num);

    @GET("MusicServlet")
    Flowable<HttpResult<List<Music>>> music(@Query("date") String date);

    @GET("MovieServlet")
    Flowable<HttpResult<List<Movie>>> movie(@Query("date") String date);
}