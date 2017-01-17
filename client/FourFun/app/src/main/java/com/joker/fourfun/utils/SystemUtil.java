package com.joker.fourfun.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.joker.fourfun.FourFun;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
     * 获取项目缓存路径
     * @return
     */
    public static String getCacheFileDirPath() {
        Logger.e(FourFun.getInstance().getApplicationContext().getCacheDir().getPath());
        Logger.e(FourFun.getInstance().getApplicationContext().getCacheDir().getAbsolutePath());
        return FourFun.getInstance().getApplicationContext().getCacheDir().getPath();
    }

    /**
     * toast 优化显示
     *
     * @param content
     */
    public static void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = Toast.makeText(FourFun.getInstance().getContext(), content, Toast
                    .LENGTH_SHORT);
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
     *
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

    /**
     * 输入流转文件
     * @param file
     * @param stream
     */
    public static void inputStream2file(File file, InputStream stream) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = stream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
