package com.joker.fourfun;

/**
 * Created by joker on 2016/12/20.
 */

public class Constants {
    public static final String MAIN_ACTIVITY_BUNDLE = "main_bundle";
    public static final String MEDIA_BUNDLE = "media_bundle";
    public static final String MOVIE_BUNDLE = "movie_bundle";
    public static final String PICTURE_BUNDLE = "picture_bundle";
    public static final String PICTURE_DETAILS_BUNDLE = "picture_details_bundle";
    public static final String ZHIHU_IMG = "zhihu_img";
    public static final String PICTURE_ONE_IMG = "picture_one";
    public static final String PICTURE_DETAILS_IMG = "picture_details_one";
    public static final String MOVIE_DETAILS_BEAN = "movie_detail_bean";
    public static final String PICTURE_DETAILS_ONE_POSITION = "picture_one_details";
    public static final String TRANSIT_PIC = "transit_pic";

    // 注册登录状态码
    public static final int REGISTER_SUCCESS_CODE = 1050;
    public static final int LOGIN_SUCCESS_CODE = 1051;

    // 注册登录状态信息
    public static final String REGISTER_SUCCESS_MESSAGE = "注册成功";
    public static final String LOGIN_SUCCESS_MESSAGE = "登录成功";

    // 正则
    // 非空，非空格
    public static final String REGEXP_EMPTY = "[^\\s]{1,}";
    // 邮箱
    public static final String REGEXP_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    // SharedPreference 键值
    public static final String LOGIN_STATE = "login_state";
}
