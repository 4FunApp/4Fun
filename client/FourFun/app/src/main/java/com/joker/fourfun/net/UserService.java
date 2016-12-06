package com.joker.fourfun.net;

import com.joker.fourfun.model.ArDaily;
import com.joker.fourfun.model.PicOne;
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
    Flowable<HttpResult<List<PicOne>>> picOne(@Query("date") String date);

    @GET("PicOneServlet")
    Flowable<HttpResult<List<PicOne>>> picOneNum(@Query("num") String num);

    @GET("ArDailyServlet")
    Flowable<HttpResult<List<ArDaily>>> arDaily(@Query("num") String num);
}