package com.neddevteam.costumefrenzy.test;

import com.neddevteam.costumefrenzy.event.Event;

/**
 * Created by mcat on 7/02/15.
 */
public class TestEvent implements Event {
    private int a = 0;
    public TestEvent(int a){
        this.a = a;
    }

    public int getA() {
        return a;
    }


}
