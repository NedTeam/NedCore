package com.neddevteam.costumefrenzy.button;

import com.neddevteam.costumefrenzy.utils.BoundingBox;
import com.neddevteam.costumefrenzy.utils.Point;

import java.util.UUID;

/**
 * Created by mcat on 8/02/15.
 */
public class SquareButton extends Button {

    private BoundingBox box;
    private UUID uuid;

    public SquareButton(Point p1,Point p2){
        super();
        box = new BoundingBox(p1,p2);
        uuid = UUID.randomUUID();
    }

    @Override
    public BoundingBox getBoundingBox() {
        return box;
    }

    @Override
    public boolean checkClicked(int xrel, int yrel) {
        return true;//Always true
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
