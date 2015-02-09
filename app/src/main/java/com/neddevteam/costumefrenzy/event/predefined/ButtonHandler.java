package com.neddevteam.costumefrenzy.event.predefined;

import com.neddevteam.costumefrenzy.event.predefined.ButtonPressedEvent;
import com.neddevteam.costumefrenzy.button.event.ButtonReleasedEvent;
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
