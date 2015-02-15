package com.neddevteam.nedcore.layer;

import android.graphics.Bitmap;

import com.neddevteam.nedcore.render.BitmapDrawable;
import com.neddevteam.nedcore.utils.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdefermin on 10/23/14.
 */
public class RenderingLayer implements Layer {

    private int priority;
    private List<BitmapDrawable> bitmaps;
    private boolean staticLayer;

    public RenderingLayer(int priority){
        this(priority,true);
    }
    public RenderingLayer(int priority, boolean staticLayer){
        this.priority = priority;
        this.staticLayer = staticLayer;
        bitmaps = new ArrayList<>();
    }

    public RenderingLayer addBitmap(BitmapDrawable bitmap){
        bitmaps.add(bitmap);
        return this;
    }

    public RenderingLayer addBitmap(Bitmap bitmap, Point position){
        bitmaps.add(new BitmapDrawable(position,bitmap));
        return this;
    }

    public RenderingLayer addBitmap(Bitmap bitmap){
        return addBitmap(bitmap,new Point(0,0));
    }

    public void removeBitmap(Point position){
        bitmaps.remove(position);
    }

    public void moveBitmap(BitmapDrawable object, Point destination){
        for(BitmapDrawable draw:bitmaps){
            if(draw.equals(object)){
                draw.setPosition(destination);
            }
        }
    }

    @Override
    public boolean isDrawable() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public boolean isStaticLayer() { return staticLayer; }

    public List<BitmapDrawable> getBitmaps() {
        return bitmaps;
    }
}
