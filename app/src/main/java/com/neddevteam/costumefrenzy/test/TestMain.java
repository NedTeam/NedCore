package com.neddevteam.costumefrenzy.test;

import com.neddevteam.costumefrenzy.event.EventManager;

/**
 * Created by mcat on 7/02/15.
 */
public class TestMain {
    public static void main(String args[]){
        System.out.println("Test");
        EventManager.register(TestEventHandler.class);
        EventManager.callEvent(new TestEvent(5));
    }
}
