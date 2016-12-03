package com.joker.fourfun.net;

import com.joker.fourfun.model.ArDaily;
import com.joker.fourfun.model.PicOne;
import com.joker.fourfun.model.Zhihu;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by joker on 2016/11/27.
 */

public interface UserService {
    @GET("PicZhihuServlet")
    Flowable<HttpResult<List<Zhihu>>> zhihuPic();

    @GET("PicOneServlet")
    Flowable<HttpResult<List<PicOne>>> picOne(@Field("num") String num);

    @GET("ArDailyServlet")
    Flowable<HttpResult<List<ArDaily>>> arDaily(@Field("num") String num);
}