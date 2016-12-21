package com.joker.fourfun.widget;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by joker on 2016/12/6.
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @SuppressLint("NewApi")
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) {
            // [-Infinity,-1)
            view.setAlpha(0);

        } else if (position <= 1) {
            // [-1,1] //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float verMargin = pageHeight * (1 - scaleFactor) / 2;
            float horMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horMargin - verMargin / 2);
            } else {
                view.setTranslationX(-horMargin + verMargin / 2);
            }

            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                    / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else {
            // (1,+Infinity]
            view.setAlpha(0);
        }
    }

}
