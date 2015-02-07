package com.neddevteam.costumefrenzy.render;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.neddevteam.costumefrenzy.layer.Layer;
import com.neddevteam.costumefrenzy.utils.LayerComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mcat on 7/02/15.
 */
public class RenderingView extends View{
    View w = null;
    List<Layer> layers = new ArrayList<Layer>();

    public RenderingView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){
        Collections.sort(layers, new LayerComparator());
        RenderingManager.renderLayers(canvas,layers);
    }

    public void addLayer(Layer l){
        layers.add(l);
    }


}
