package com.neddevteam.costumefrenzy.layer;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.neddevteam.costumefrenzy.utils.JSONUtils;
import com.neddevteam.costumefrenzy.utils.RenderingUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gdefermin on 10/23/14.
 */
public class RenderingLayer implements Layer {

    private int imageResourceID;
    private String imageResourceName;
    private String dataResourceName;
    private AssetManager am;

    private boolean dynamic;

    public RenderingLayer(int imageResourceID){
        this.imageResourceID=imageResourceID;
    }

    public RenderingLayer(String imageResourceName, String dataResourceName, AssetManager am){
        this.imageResourceName = imageResourceName;
        this.dataResourceName = dataResourceName;
        this.am = am;
        parse();
    }


    @Override
    public boolean isRenderable() {
        return true;
    }

    @Override
    public int getPriority() {
        //Stub
        return 0;
    }

    @Override
    public Bitmap getImage() {
        return null;
    }


    private void parse() {
        if(dataResourceName==null)return;
        try {
            JSONObject object = new JSONObject(JSONUtils.loadStringFromAsset(am, dataResourceName));
            Bitmap map = (imageResourceID==0) ? RenderingUtils.parseRender(object, imageResourceName) :
                    RenderingUtils.parseRender(object,imageResourceID);
        } catch (JSONException e) {
            Log.e("Snake-Remake",e.getMessage());
        }
    }

    public boolean isDynamic() {
        return dynamic;
    }
}
