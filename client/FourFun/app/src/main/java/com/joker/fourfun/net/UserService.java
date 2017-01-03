package com.joker.fourfun.net;

import com.joker.fourfun.model.ArticleOne;
import com.joker.fourfun.model.LoginInfo;
import com.joker.fourfun.model.Movie;
import com.joker.fourfun.model.Music;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.model.User;
import com.joker.fourfun.model.Zhihu;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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
    Flowable<HttpResult<List<ArticleOne>>> article(@Query("date") String date);

    @GET("MusicServlet")
    Flowable<HttpResult<List<Music>>> music(@Query("date") String date);

    @Streaming
    @GET
    Flowable<ResponseBody> downloadMusic(@Url String url);

    @GET("MovieServlet")
    Flowable<HttpResult<List<Movie>>> movie(@Query("date") String date);

    @GET("LoginServlet")
    Flowable<HttpResult<List<LoginInfo>>> login(@Query("userName") String username, @Query("password")
            String password);

    @POST("RegisterServlet")
    Flowable<HttpResult<List<LoginInfo>>> register(@Body User user);
}