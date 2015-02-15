package com.neddevteam.nedcore.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;

import com.neddevteam.nedcore.layer.Layer;
import com.neddevteam.nedcore.utils.LayerComparator;
import com.neddevteam.nedcore.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcat on 7/02/15.
 */
public class RenderingView extends View{

    private List<Layer> layers = new ArrayList<Layer>();
    private LayerComparator comparator = new LayerComparator();

    public RenderingView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        /*Collections.sort(layers, new LayerComparator());*/
        RenderingManager.renderLayers(canvas,layers);
    }

    public void addLayer(Layer l){
        CollectionUtils.sortedInsertion(layers, comparator, l);
    }

    public List<Layer> getLayers(){
        return layers;
    }

    public void removeLayer(int i) {
        layers.remove(i);
    }
}
