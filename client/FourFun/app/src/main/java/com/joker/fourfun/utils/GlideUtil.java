package com.joker.fourfun.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by joker on 2016/12/6.
 */

public class GlideUtil {
    public static void setImage(Context context, String imageUrl, ImageView targetView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .thumbnail(0.5f)
                .centerCrop()
                .into(targetView);
    }
}
