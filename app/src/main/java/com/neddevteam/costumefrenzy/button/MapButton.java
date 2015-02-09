package com.neddevteam.costumefrenzy.button;

import android.graphics.Bitmap;

import com.neddevteam.costumefrenzy.utils.BitmapUtils;
import com.neddevteam.costumefrenzy.utils.BoundingBox;
import com.neddevteam.costumefrenzy.utils.Point;

/**
 * Created by mcat on 8/02/15.
 */
public class MapButton extends Button {

    private Bitmap bitmap;
    private Point p1;

    public MapButton(Bitmap bitmap,Point p1){
        super();
        this.p1 = p1;
        this.bitmap = bitmap;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(p1,bitmap.getWidth(),bitmap.getHeight());
    }

    @Override
    public boolean checkClicked(int xRel, int yRel) {
        return BitmapUtils.checkAlpha(bitmap,xRel,yRel);
    }

}
