package com.joker.fourfun.net;

/**
 * Created by joker on 2016/11/28.
 */

public class ApiException extends RuntimeException {
    // 服务器数据错误
    public static final int MESSAGE_NOT_FOUND = 100;

    public ApiException(int resultCode) {
        getApiExceptionMessage(resultCode);
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case MESSAGE_NOT_FOUND:
                message = "好像出现了一点问题哦";
                break;
            default:
                message = "未知错误";
        }

        return message;
    }
}
