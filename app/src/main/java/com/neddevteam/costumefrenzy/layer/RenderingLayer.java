package com.neddevteam.costumefrenzy.layer;

import android.graphics.Bitmap;

/**
 * Created by gdefermin on 10/23/14.
 */
public class RenderingLayer implements Layer {

    private int priority;
    private Bitmap bitmap;

    public RenderingLayer(Bitmap bitmap, int priority){
        this.bitmap = bitmap;
        this.priority = priority;
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

}
