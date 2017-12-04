package com.xin.xiaoxinzone.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by Administrator on 2017/10/19.
 */

public class ResourceTools {

    public static int getColor(Context context,int color){

        if (Build.VERSION.SDK_INT >= 23){
            return context.getColor(color);
        }else {
            return context.getResources().getColor(color);
        }
    }

    public static Drawable getDrawable(Context context, int drawable){

        if (Build.VERSION.SDK_INT >= 23){
            return context.getDrawable(drawable);
        }else {
            return context.getResources().getDrawable(drawable);
        }
    }

}
