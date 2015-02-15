package com.neddevteam.nedcore.utils;

import com.neddevteam.nedcore.layer.Layer;

import java.util.Comparator;

/**
 * Created by mcat on 7/02/15.
 */
public class LayerComparator implements Comparator<Layer>{

    /**
     * Integer comparison between {@code lhs} priority level and {@code rhs} priority level
     *
     * @param lhs first layer to compare
     * @param rhs second layer to compare
     * @return an int
     */
    @Override
    public int compare(Layer lhs, Layer rhs) {
        return Integer.compare(lhs.getPriority(),rhs.getPriority());
    }
}
