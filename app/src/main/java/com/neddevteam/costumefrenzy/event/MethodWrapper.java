package com.neddevteam.costumefrenzy.event;

/**
 * Created by mcat on 10/02/15.
 */

import java.lang.reflect.Method;

/**
 * Created only for internal purposes. Do not use
 */
public class MethodWrapper {

    private final Method m;
    private final EventPriority p;

    protected MethodWrapper(Method m,EventPriority p){
        this.m = m;
        this.p = p;
    }

    protected Method getM() {
        return m;
    }

    protected EventPriority getP() {
        return p;
    }
}
