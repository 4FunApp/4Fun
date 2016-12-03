package com.joker.fourfun.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.joker.fourfun.FourFun;

/**
 * Created by joker on 2016/11/27.
 */
public class SystemUtil {
    private static Toast mToast;

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) FourFun.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }

        mToast.show();
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
