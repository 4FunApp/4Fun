package com.joker.fourfun.net;

/**
 * Created by joker on 2016/11/28.
 */

public class HttpResult<T> {
    private boolean error;
    private T result;

    public T getResult() {
        return result;
    }

    public boolean isError() {
        return error;
    }
}
