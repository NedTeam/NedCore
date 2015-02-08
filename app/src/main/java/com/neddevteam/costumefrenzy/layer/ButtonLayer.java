package com.neddevteam.costumefrenzy.layer;

import com.neddevteam.costumefrenzy.button.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcat on 8/02/15.
 */
public class ButtonLayer implements Layer{

    private List<Button> buttonList = new ArrayList<Button>();
    private int priority;
    public ButtonLayer(int priority){
        this.priority = priority;
    }

    @Override
    public boolean isDrawable() {
        return false;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public void addButton(Button b){
        buttonList.add(b);
    }

    public List<Button> getButtons(){
        return buttonList;
    }
}
