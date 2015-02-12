package com.neddevteam.nedcore.utils;

import com.neddevteam.nedcore.layer.Layer;

import java.util.Comparator;

/**
 * Created by mcat on 7/02/15.
 */
public class LayerComparator implements Comparator<Layer>{
    @Override
    public int compare(Layer lhs, Layer rhs) {
        return Integer.compare(lhs.getPriority(),rhs.getPriority());
    }
}
