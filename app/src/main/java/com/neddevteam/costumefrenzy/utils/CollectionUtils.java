package com.neddevteam.costumefrenzy.utils;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mcat on 10/02/15.
 */
public class CollectionUtils {
    public static <T> List<T> sortedInsertion(List<T> list, Comparator<T> comparator,T elem){
        if(list.size()<=0)
            list.add(elem);
        else{
            addLayerRecursive(list,comparator,elem,list.size()/2);
        }
        return list;
    }

    private static <T> void addLayerRecursive(List<T> list,Comparator<T> comparator,T elem,
                                              int currentPos){
        T elem2 = list.get(currentPos);
        int comparison = comparator.compare(elem,elem2);

        if(comparison == 0) {
            list.add(currentPos, elem);
        }
        else if(comparison < 0) {
            if (currentPos == 0){
                list.add(0,elem);
            }
            if(comparator.compare(elem,list.get(currentPos-1)) > 0){
                list.add(currentPos,elem);
            }
            int newPos = currentPos / 2;
            addLayerRecursive(list, comparator, elem, newPos);
        }
        else {
            if (currentPos >= list.size()-1){
                list.add(elem);
            }
            if(comparator.compare(elem,list.get(currentPos+1)) < 0){
                list.add(currentPos+1,elem);
            }
            int newPos = currentPos + 3 / 2;
            addLayerRecursive(list,comparator,elem,newPos);
        }
    }
}
