package com.neddevteam.costumefrenzy.event.predefined;

import com.neddevteam.costumefrenzy.button.Button;
import com.neddevteam.costumefrenzy.event.Event;

/**
 * Created by gdefermin on 2/9/15.
 */
public class ButtonReleasedEvent implements Event {
    private Button button;

    public ButtonReleasedEvent(Button button){
        this.button = button;
    }

    public Button getButton() {
        return button;
    }
}
