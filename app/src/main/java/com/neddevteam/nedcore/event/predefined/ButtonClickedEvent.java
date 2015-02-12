package com.neddevteam.nedcore.event.predefined;

import com.neddevteam.nedcore.button.Button;
import com.neddevteam.nedcore.event.Event;

/**
 * Created by mcat on 2/9/15.
 */

public class ButtonClickedEvent extends Event {

    private Button button;

    public ButtonClickedEvent(Button button){
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}
