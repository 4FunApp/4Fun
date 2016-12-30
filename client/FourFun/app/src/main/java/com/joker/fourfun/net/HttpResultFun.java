package com.joker.fourfun.net;

import io.reactivex.functions.Function;

/**
 * Created by joker on 2016/11/28.
 */

public class HttpResultFun<T> implements Function<HttpResult<T>, T> {
    @Override
    public T apply(HttpResult<T> tHttpResult) throws Exception {
        if (tHttpResult == null) {
            throw new ConnectErrorException(ConnectErrorException.TIME_OUT_ERROR);
        }

        if (tHttpResult.isError()) {
            throw new ApiException(ApiException.MESSAGE_NOT_FOUND);
        }

        return tHttpResult.getResult();
    }
}
