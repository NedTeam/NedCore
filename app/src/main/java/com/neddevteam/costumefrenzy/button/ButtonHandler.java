package com.neddevteam.costumefrenzy.button;

import com.neddevteam.costumefrenzy.button.Button;
import com.neddevteam.costumefrenzy.event.Event;
import com.neddevteam.costumefrenzy.event.HandleEvent;
import com.neddevteam.costumefrenzy.event.Listener;

/**
 * Created by gdefermin on 2/9/15.
 */
public class ButtonHandler implements Listener {

    @HandleEvent
    public void onButtonPressed(ButtonPressedEvent e){

    }

    @HandleEvent
    public void onButtonReleased(ButtonReleasedEvent e){

    }
}
