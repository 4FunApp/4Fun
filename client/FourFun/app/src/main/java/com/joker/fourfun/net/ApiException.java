package com.joker.fourfun.net;

/**
 * Created by joker on 2016/11/28.
 */

public class ApiException extends RuntimeException {
    public static final int MESSAGE_NOT_FOUND = 100;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    private static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case MESSAGE_NOT_FOUND:
                message = "服务器数据错误";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}
