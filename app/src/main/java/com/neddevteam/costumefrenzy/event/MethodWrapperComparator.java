package com.neddevteam.costumefrenzy.event;

/**
 * Created by mcat on 10/02/15.
 */
public class MethodWrapperComparator implements java.util.Comparator<MethodWrapper> {

    @Override
    public int compare(MethodWrapper lhs, MethodWrapper rhs) {
        return Integer.compare(lhs.getP().ordinal(),rhs.getP().ordinal());
    }
}

