package com.neddevteam.costumefrenzy.layer;

import android.graphics.Bitmap;

/**
 * Created by gdefermin on 10/23/14.
 */
public class RenderingLayer implements Layer {

    private int priority;
    private Bitmap bitmap;
    private boolean staticLayer;

    public RenderingLayer(Bitmap bitmap, int priority){
        this(bitmap,priority,true);
    }
    public RenderingLayer(Bitmap bitmap, int priority, boolean staticLayer){
        this.bitmap = bitmap;
        this.priority = priority;
        this.staticLayer = staticLayer;
    }

    @Override
    public boolean isDrawable() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public boolean isStaticLayer() { return staticLayer; }


}
