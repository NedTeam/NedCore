package com.neddevteam.costumefrenzy.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.neddevteam.costumefrenzy.layer.RenderingLayer;
import com.neddevteam.costumefrenzy.render.RenderingView;
import com.neddevteam.costumefrenzy.utils.BitmapUtils;

import costumefrenzy.nedteam.com.costumefrenzy.R;

/**
 * Created by mcat on 7/02/15.
 */
public class testActivity extends Activity {
    public testActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        final Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.square);
        final Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        final Bitmap bitmap4 = bitmap1.copy(bitmap1.getConfig(),true);
        final RenderingView view = new RenderingView(getBaseContext());
        view.addLayer(new RenderingLayer(bitmap2,-1));
        view.addLayer(new RenderingLayer(bitmap4,1));
        view.addLayer(new RenderingLayer(bitmap3, 0));
        setContentView(view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    BitmapUtils.setColorToAll(bitmap4, 0xff000000);
                } catch (InterruptedException e) {}
                view.postInvalidate();
            }
        }).start();
    }
}
