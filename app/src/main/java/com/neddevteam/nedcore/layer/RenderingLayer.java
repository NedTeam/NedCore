package com.neddevteam.nedcore.layer;

import android.graphics.Bitmap;

import com.neddevteam.nedcore.utils.Point;

import java.util.HashMap;

/**
 * Created by gdefermin on 10/23/14.
 */
public class RenderingLayer implements Layer {

    private int priority;
    private HashMap<Point,Bitmap> bitmaps;
    private boolean staticLayer;

    public RenderingLayer(Bitmap bitmap, int priority){
        this(bitmap,priority,true);
    }
    public RenderingLayer(Bitmap bitmap, int priority, boolean staticLayer){
        this(bitmap,new Point(0,0), priority, staticLayer);
    }
    public RenderingLayer(Bitmap bitmap, Point position, int priority){
        this(bitmap,position, priority, true);
    }
    public RenderingLayer(Bitmap bitmap, Point position, int priority, boolean staticLayer){
        bitmaps = new HashMap<>();
        bitmaps.put(position, bitmap);
        this.priority = priority;
        this.staticLayer = staticLayer;
    }
    public RenderingLayer(int priority, boolean staticLayer){
        bitmaps = new HashMap<>();
        this.priority = priority;
        this.staticLayer = staticLayer;
    }

    public void addBitmap(Bitmap bitmap, Point position){
        bitmaps.put(position, bitmap);
    }

    public void removeBitmap(Point position){
        bitmaps.remove(position);
    }

    public void moveBitmap(Point origin, Point destination){
        addBitmap(bitmaps.get(origin), destination);
        removeBitmap(origin);
    }

    @Override
    public boolean isDrawable() {
        return true;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public HashMap<Point,Bitmap> getBitmaps() {
        return bitmaps;
    }

    public boolean isStaticLayer() { return staticLayer; }

}
