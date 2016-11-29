package com.joker.fourfun.net;

import com.joker.fourfun.model.Zhihu;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by joker on 2016/11/27.
 */

public interface UserService {
    @GET("PicZhihuServlet")
    Flowable<HttpResult<Zhihu>> ZhihuPic();
}