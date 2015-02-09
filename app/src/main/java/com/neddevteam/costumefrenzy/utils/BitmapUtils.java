package com.neddevteam.costumefrenzy.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by mcat on 7/02/15.
 */
public class BitmapUtils {
    public static Bitmap setColorToAll(Bitmap bitmap,int color){
        for(int i=0;i<bitmap.getWidth();i++){
            for(int j=0;j<bitmap.getHeight();j++){
                int alpha = (Color.alpha(bitmap.getPixel(i,j)));
                if(alpha!=0){
                    bitmap.setPixel(i,j,color);
                }

            }
        }
        return bitmap;
    }

    public static boolean checkAlpha(Bitmap bitmap, int xrel, int yrel) {
        return Color.alpha(bitmap.getPixel(xrel,yrel)) != 0;
    }
}
