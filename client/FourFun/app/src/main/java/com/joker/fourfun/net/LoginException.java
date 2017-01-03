package com.joker.fourfun.net;

/**
 * Created by joker on 2016/12/31.
 */

public class LoginException extends RuntimeException {
    public static final int LOGIN_PASSWORD_ERROR_CODE = 1025;
    public static final int LOGIN_USER_ERROR_CODE = 1026;
    public static final int REGISTER_USER_REPEAT_ERROR_CODE = 1027;
    public static final String LOGIN_PASSWORD_ERROR_MESSAGE = "密码错误，请重试";
    public static final String LOGIN_USER_ERROR_MESSAGE = "用户名不存在";
    public static final String REGISTER_USER_REPEAT_ERROR_MESSAGE = "用户已注册";


    public LoginException(int code) {
        this(getLoginExceptionMessage(code));
    }

    public LoginException(String message) {
        super(message);
    }

    private static String getLoginExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case LOGIN_PASSWORD_ERROR_CODE:
                message = LOGIN_PASSWORD_ERROR_MESSAGE;
                break;
            case LOGIN_USER_ERROR_CODE:
                message = LOGIN_USER_ERROR_MESSAGE;
                break;
            case REGISTER_USER_REPEAT_ERROR_CODE:
                message = REGISTER_USER_REPEAT_ERROR_MESSAGE;
                break;
            default:
                message = "未知错误";
                break;
        }

        return message;
    }
}
