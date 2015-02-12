package com.neddevteam.nedcore.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.neddevteam.nedcore.layer.Layer;
import com.neddevteam.nedcore.layer.RenderingLayer;
import com.neddevteam.nedcore.utils.Point;

import java.util.List;
import java.util.Map;

/**
 * Created by mcat on 7/02/15.
 */
public class RenderingManager {

    public static void renderLayers(Canvas canvas,List<Layer> layers){
        //Log.i("CostumeFrenzy","Rendering!");
        for(Layer l:layers){
            if(l instanceof RenderingLayer){
                RenderingLayer layer = (RenderingLayer) l;
                for(Map.Entry<Point,Bitmap> entry: layer.getBitmaps().entrySet()) {
                    canvas.drawBitmap(
                            entry.getValue(),
                            entry.getKey().getX(),
                            entry.getKey().getY(),
                            new Paint()
                    );
                }
            }
        }
    }

}