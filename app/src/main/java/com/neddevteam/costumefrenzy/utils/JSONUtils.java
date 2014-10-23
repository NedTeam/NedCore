package com.neddevteam.costumefrenzy.utils;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by mcat on 21/10/14.
 */
public class JSONUtils {
    public static String loadStringFromAsset(AssetManager am, String filename) {
        String json;
        try {
            InputStream is = am.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}