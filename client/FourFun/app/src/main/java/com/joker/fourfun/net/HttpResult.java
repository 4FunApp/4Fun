package com.joker.fourfun.net;

import java.util.List;

/**
 * Created by joker on 2016/11/28.
 */

public class HttpResult<T> {
    private boolean error;
    private List<T> result;

    public T getResult() {
        return result.get(0);
    }

    public boolean isError() {
        return error;
    }
}
