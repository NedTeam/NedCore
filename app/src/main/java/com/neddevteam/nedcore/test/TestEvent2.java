package com.neddevteam.nedcore.test;

import com.neddevteam.nedcore.event.Event;

/**
 * Created by mcat on 7/02/15.
 */
public class TestEvent2 extends Event {
    private int a = 0;
    public TestEvent2(int a){
        this.a = a;
    }

    public int getA() {
        return a;
    }


}
