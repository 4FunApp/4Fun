package com.joker.fourfun.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.joker.fourfun.FourFun;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by joker on 2016/11/27.
 */
public class SystemUtil {
    public static final String RUNNING_FONT = "running_font.ttf";
    private static Toast mToast;

    private SystemUtil() {
    }

    /**
     * 网络是否连接
     *
     * @return
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) FourFun.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * toast 优化显示
     *
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
        }

        mToast.show();
    }

    /**
     * 取消 toast
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    /**
     * 前几天的日期
     *
     * @param before 和今天相差的天数
     * @return
     */
    public static String beforeToday(int before) {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA); //设置时间格式

        if (before < 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.DATE, before);
            now = calendar.getTime();
        }

        return sdf.format(now);
    }

    /**
     * 像素转 sp
     *
     * @param context
     * @param textSizePixel
     * @return
     */
    public static int px2sp(Context context, float textSizePixel) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (textSizePixel / fontScale + 0.5f);
    }

    /**
     * sp 转 px
     *
     * @param context
     * @param textSizeSp
     * @return
     */
    public static float sp2px(Context context, float textSizeSp) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (textSizeSp * fontScale + 0.5f);
    }

    /**
     * dp 转 px
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        float DENSITY = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * DENSITY);
    }

    public static Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/" + RUNNING_FONT);
    }
}
