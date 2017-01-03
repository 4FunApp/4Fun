package com.joker.fourfun.login.contract;

import android.content.Context;

/**
 * Created by joker on 2016/12/31.
 */

public interface UserState {
    // 收藏
    void collect(Context context);

    // 投稿
    void submit(Context context);
}
