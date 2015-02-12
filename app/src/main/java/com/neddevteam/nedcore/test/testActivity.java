package com.neddevteam.nedcore.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.neddevteam.nedcore.activity.GameActivity;
import com.neddevteam.nedcore.button.Button;
import com.neddevteam.nedcore.button.MapButton;
import com.neddevteam.nedcore.event.EventManager;
import com.neddevteam.nedcore.layer.ButtonLayer;
import com.neddevteam.nedcore.layer.RenderingLayer;
import com.neddevteam.nedcore.render.RenderingView;
import com.neddevteam.nedcore.utils.BitmapUtils;
import com.neddevteam.nedcore.utils.Point;

import java.util.Random;

import costumefrenzy.nedteam.com.costumefrenzy.R;

/**
 * Created by mcat on 7/02/15.
 */
public class testActivity extends GameActivity {

    public testActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Action bar
        getActionBar().hide();
        EventManager.register(TestEventHandler.class);
        //Bitmaps
        final Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        final Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.square);
        final Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        final Bitmap bitmap4 = bitmap1.copy(bitmap1.getConfig(), true);
        //Buttons
        final ButtonLayer buttonLayer = new ButtonLayer(10);
        final Button testButton = new MapButton(bitmap3,new Point(0,0));
        buttonLayer.addButton(testButton);
        //Add layers
        RenderingView view = getView();
        view.addLayer(new RenderingLayer(bitmap2,-1));

        RenderingLayer testLayer = new RenderingLayer(bitmap4,1);
        testLayer.addBitmap(bitmap4, new Point(300,400));
        view.addLayer(testLayer);
        
        view.addLayer(new RenderingLayer(bitmap3, 0));
        view.addLayer(buttonLayer);
        setContentView(view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                while(true){
                    int color = 0xff000000 + 256 * 256 * random.nextInt(256) + 256 * random.nextInt(256)
                        + random.nextInt(256);
                    try {
                        Thread.sleep(1000);
                        BitmapUtils.setColorToAll(bitmap4, color);
                    } catch (InterruptedException e) {}
                    getView().postInvalidate();
                }

            }
        }).start();
    }


}
