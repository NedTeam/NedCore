package com.neddevteam.costumefrenzy.render;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.neddevteam.costumefrenzy.layer.Layer;
import com.neddevteam.costumefrenzy.layer.RenderingLayer;

import java.util.List;

/**
 * Created by mcat on 7/02/15.
 */
public class RenderingManager {

    public static void renderLayers(Canvas canvas,List<Layer> layers){
        for(Layer l:layers){
            if(l instanceof RenderingLayer){
                RenderingLayer layer = (RenderingLayer) l;
                canvas.drawBitmap(layer.getBitmap(),0,0,new Paint());
            }
        }
    }

}
