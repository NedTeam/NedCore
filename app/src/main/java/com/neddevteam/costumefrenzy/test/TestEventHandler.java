package com.neddevteam.costumefrenzy.test;

import com.neddevteam.costumefrenzy.event.HandleEvent;
import com.neddevteam.costumefrenzy.event.Listener;

/**
 * Created by mcat on 7/02/15.
 */
public class TestEventHandler implements Listener {

    @HandleEvent
    public void testEvent(TestEvent test){
        System.out.println("Evento:"+test.getA());
    }

    @HandleEvent
    public void testEvent(TestEvent2 test){
        System.out.println("Evento2:"+test.getA());
    }
}
