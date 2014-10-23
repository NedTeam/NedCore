package com.neddevteam.costumefrenzy.layer;

import android.graphics.Bitmap;

/**
 * Created by gdefermin on 10/23/14.
 */
public interface Layer {

    public boolean isRenderable();

    public int getPriority();

    public Bitmap getImage();

}
