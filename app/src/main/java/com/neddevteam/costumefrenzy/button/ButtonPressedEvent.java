package com.neddevteam.costumefrenzy.button;

import com.neddevteam.costumefrenzy.event.Event;

/**
 * Created by gdefermin on 2/9/15.
 */
public class ButtonPressedEvent implements Event {

    private Button button;

    public ButtonPressedEvent(Button button){
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}
