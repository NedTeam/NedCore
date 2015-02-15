package com.neddevteam.nedcore.render;

import android.graphics.Canvas;

import com.neddevteam.nedcore.layer.Layer;
import com.neddevteam.nedcore.layer.RenderingLayer;

import java.util.List;

/**
 * Created by mcat on 7/02/15.
 */
public class RenderingManager {

    public static void renderLayers(Canvas canvas,List<Layer> layers){
        //Log.i("CostumeFrenzy","Rendering!");
        for(Layer l:layers){
            if(l instanceof RenderingLayer){
                RenderingLayer layer = (RenderingLayer) l;
                for(IDrawable drawable:layer.getBitmaps()){
                    drawable.draw(canvas);
                }
            }
        }
    }

}
