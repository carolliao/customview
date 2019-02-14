package com.carol.practice.customviews.utils;

import android.content.Context;

/**
 * Created by Long on 2019/2/13.
 */

public class DensityUtil {
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
