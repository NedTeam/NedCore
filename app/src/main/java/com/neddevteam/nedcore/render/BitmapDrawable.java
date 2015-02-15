package com.neddevteam.nedcore.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.neddevteam.nedcore.utils.Point;

import java.util.UUID;

/**
 * Created by mcat on 15/02/15.
 */
public class BitmapDrawable implements IDrawable{

    private Point location;
    private Bitmap bitmap;
    private UUID uuid;
    private boolean visible = true;

    public BitmapDrawable(Point loc, Bitmap bitmap) {
        this.location = loc;
        this.bitmap = bitmap;
        uuid = UUID.randomUUID();
    }

    @Override
    public void draw(Canvas c) {
        if(visible) c.drawBitmap(bitmap,location.getX(),location.getY(),new Paint());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof BitmapDrawable){
            return uuid.equals(((BitmapDrawable)o).getUUID());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 37 * hash + location.hashCode();
        hash = 37 * hash + uuid.hashCode();
        return hash;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setPosition(Point position) {
        this.location = position;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
