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
import com.neddevteam.nedcore.physics.GameObject;
import com.neddevteam.nedcore.physics.PhysicsProperties;
import com.neddevteam.nedcore.physics.World;
import com.neddevteam.nedcore.render.RenderingView;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

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
        //Physics test
        RenderingLayer layer = new RenderingLayer(bitmap4,new Point(50,500),10,true);
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        World w = new World(new Vector2f(0,-9.8f),layer, view,screenWidth,screenHeight);
        final GameObject obj = new GameObject(bitmap4,new PhysicsProperties(new Vector2f(50,500),100));
        w.addObject(obj);
        obj.applyForce(new Vector2f(0,0.0098f));
        obj.applyForce(new Vector2f(0.03f,-0.05f));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                    obj.applyForce(new Vector2f(-0.03f,0.05f));
                } catch (InterruptedException e) {}
            }
        }).start();
        //Add layers to view
        view.addLayer(layer);
        view.addLayer(new RenderingLayer(bitmap3, 0));
        view.addLayer(buttonLayer);
        //Android
        setContentView(view);
    }


}
