package com.neddevteam.nedcore.test;

import android.util.Log;

import com.neddevteam.nedcore.event.HandleEvent;
import com.neddevteam.nedcore.event.Listener;
import com.neddevteam.nedcore.event.predefined.ButtonClickedEvent;
import com.neddevteam.nedcore.event.predefined.ButtonPressedEvent;
import com.neddevteam.nedcore.event.predefined.ButtonReleasedEvent;

/**
 * Created by mcat on 7/02/15.
 */
public class TestEventHandler implements Listener {

    @HandleEvent
    public void press(ButtonPressedEvent test){
        Log.i("NedCore","Press:"+test.getButton().getUUID().toString());
    }

    @HandleEvent
    public void release(ButtonReleasedEvent test){
        Log.i("NedCore", "Release:" + test.getButton().getUUID().toString());
    }

    @HandleEvent
    public void click(ButtonClickedEvent test){
        Log.i("NedCore", "Click:" + test.getButton().getUUID().toString());
    }
}
