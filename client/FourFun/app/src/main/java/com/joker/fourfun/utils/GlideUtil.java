package com.joker.fourfun.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joker.fourfun.widget.GlideCircleTransform;

/**
 * Created by joker on 2016/12/6.
 */

public class GlideUtil {
    // 磁盘缓存图片所有大小，centerCrop 大小
    public static void setImage(Context context, String imageUrl, ImageView targetView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .thumbnail(0.5f)
                .centerCrop()
                .into(targetView);
    }

    //圆形 ImageView
    public static void setCirCleImage(Context context, String imageUrl, ImageView targetView) {
        Glide.with(context)
                .load(imageUrl)
                .transform(new GlideCircleTransform(context))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(targetView);
    }
}
