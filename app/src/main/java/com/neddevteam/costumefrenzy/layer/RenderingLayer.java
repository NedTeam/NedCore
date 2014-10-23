package com.neddevteam.costumefrenzy.layer;

import android.graphics.Bitmap;

/**
 * Created by gdefermin on 10/23/14.
 */
public class RenderingLayer implements Layer {
    private int imageResourceID;
    private int dataResourceID;
    private boolean dynamic;

    public RenderingLayer(int imageResourceID){
        this.imageResourceID = imageResourceID;
        parse();
    }

    public RenderingLayer(int imageResourceID, int dataResourceID){
        this.imageResourceID = imageResourceID;
        this.dataResourceID = dataResourceID;
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

    }

    public boolean isDynamic() {
        return dynamic;
    }
}
