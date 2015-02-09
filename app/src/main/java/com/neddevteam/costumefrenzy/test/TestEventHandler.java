package com.neddevteam.costumefrenzy.test;

import android.util.Log;

import com.neddevteam.costumefrenzy.event.predefined.ButtonClickedEvent;
import com.neddevteam.costumefrenzy.event.predefined.ButtonPressedEvent;
import com.neddevteam.costumefrenzy.button.event.ButtonReleasedEvent;
import com.neddevteam.costumefrenzy.event.HandleEvent;
import com.neddevteam.costumefrenzy.event.Listener;

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
        Log.i("NedCore","Release:"+test.getButton().getUUID().toString());
    }

    @HandleEvent
    public void click(ButtonClickedEvent test){
        Log.i("NedCore","Click:"+test.getButton().getUUID().toString());
    }
}
