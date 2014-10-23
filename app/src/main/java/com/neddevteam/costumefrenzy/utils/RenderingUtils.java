package com.neddevteam.costumefrenzy.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
        JSONArray array = null;
        Bitmap bigmap = Bitmap.createBitmap(image.getWidth()*8, image.getHeight()*8, Bitmap.Config.ARGB_8888);
        HashMap<Integer,String> map = new HashMap<Integer, String>();
        try {
            array = object.getJSONArray("ids");
            JSONObject sub = array.getJSONObject(0);
            int id = sub.getInt("id");
            String tex = sub.getString("texture");
            map.put(id,tex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                int color = image.getPixel(i,j);
                int alpha = (color & 0xFF000000);
                color = (color & 0x00FFFFFF) >> 8;
                if(alpha!=0){//If alpha == 0, we don't touch the pixel
                    String s = map.get(color);
                    if (s !=null){
                        //In bigimage map texture s to pixel (i*8 / j*8)
                    }
                }
            }
        }
        return null;
    }
}
