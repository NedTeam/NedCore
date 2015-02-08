package com.neddevteam.costumefrenzy.render;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

    private List<Layer> layers = new ArrayList<Layer>();
    private LayerComparator comparator = new LayerComparator();

    public RenderingView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        Collections.sort(layers, new LayerComparator());
        RenderingManager.renderLayers(canvas,layers);
    }

    public void addLayer(Layer l){
        layers.add(l);
    }

    private void addLayerRecursive(Layer l, int currentPos){
        //TODO
        Layer l2 = layers.get(currentPos);
        int comparison = comparator.compare(l,l2);

        if(comparison == 0)
            layers.add(currentPos, l);
        else if(comparison < 0) {
            int newPos = currentPos/2;
            addLayerRecursive(l, newPos);
        }
        else {
            int newPos = currentPos*3/2;
            addLayerRecursive(l, newPos);
        }
    }

    public void removeLayer(int i) {
        layers.remove(i);
    }
}
