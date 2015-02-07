package com.neddevteam.costumefrenzy.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.neddevteam.costumefrenzy.layer.RenderingLayer;
import com.neddevteam.costumefrenzy.render.RenderingView;

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
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.square);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.test);

        RenderingView view = new RenderingView(getBaseContext());
        view.addLayer(new RenderingLayer(bitmap2,-1));
        view.addLayer(new RenderingLayer(bitmap1,1));
        view.addLayer(new RenderingLayer(bitmap3,0));

        setContentView(view);
    }
}
