package com.neddevteam.nedcore.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mcat on 10/02/15.
 */
public class CollectionUtils {
    public static <T> List<T> sortedInsertion(List<T> list, Comparator<T> comparator,T elem){
        list.add(elem);
        Collections.sort(list,comparator); //TODO
        return list;
    }


    /*private void addLayerRecursive(Layer l, int currentPos){
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
    }*/
}
