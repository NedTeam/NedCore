package com.neddevteam.nedcore.button;

import com.neddevteam.nedcore.layer.ButtonLayer;
import com.neddevteam.nedcore.layer.Layer;
import com.neddevteam.nedcore.render.RenderingView;
import com.neddevteam.nedcore.utils.Point;

/**
 * Created by mcat on 8/02/15.
 */
public class ButtonManager {
    public static Button checkClick(RenderingView w,int x,int y){
        // Is this in the correct order?
        for(Layer l:w.getLayers()){
            if(l instanceof ButtonLayer){
                for(Button button:((ButtonLayer) l).getButtons()){
                    if(button.getBoundingBox().checkInside(x,y)){
                        Point corner = button.getBoundingBox().getP1();
                        int xRel = x-corner.getX();
                        int yRel = y-corner.getY();
                        if(button.checkClicked(xRel,yRel)){
                            return button;
                        }
                    }
                }
                break;
            }
        }
        return null;
    }
}
