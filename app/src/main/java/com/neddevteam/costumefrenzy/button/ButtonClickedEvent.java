package com.neddevteam.costumefrenzy.button;

import com.neddevteam.costumefrenzy.event.Event;

/**
 * Created by mcat on 2/9/15.
 */

public class ButtonClickedEvent implements Event {

    private Button button;

    public ButtonClickedEvent(Button button){
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}
