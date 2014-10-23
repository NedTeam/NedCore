package com.neddevteam.costumefrenzy.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;

/**
 * Created by mcat on 23/10/14.
 */
public class RenderingUtils {
    public static Bitmap parseRender(Resources ctx,JSONObject object, int imageResourceID) {
        Bitmap source = BitmapFactory.decodeResource(ctx,imageResourceID);
        return parse(source,object);
    }
    public static Bitmap parseRender(JSONObject object, String imageResourceName) {
        Bitmap source = BitmapFactory.decodeFile(imageResourceName);
        return parse(source,object);
    }

    private static Bitmap parse(Bitmap image, JSONObject object){
        return null;
    }
}
