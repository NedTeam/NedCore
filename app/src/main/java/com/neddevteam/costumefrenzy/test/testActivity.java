package com.neddevteam.costumefrenzy.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;

import com.neddevteam.costumefrenzy.button.Button;
import com.neddevteam.costumefrenzy.event.predefined.ButtonClickedEvent;
import com.neddevteam.costumefrenzy.button.ButtonManager;
import com.neddevteam.costumefrenzy.event.predefined.ButtonPressedEvent;
import com.neddevteam.costumefrenzy.button.event.ButtonReleasedEvent;
import com.neddevteam.costumefrenzy.button.MapButton;
import com.neddevteam.costumefrenzy.event.EventManager;
import com.neddevteam.costumefrenzy.layer.ButtonLayer;
import com.neddevteam.costumefrenzy.layer.RenderingLayer;
import com.neddevteam.costumefrenzy.render.RenderingView;
import com.neddevteam.costumefrenzy.utils.BitmapUtils;
import com.neddevteam.costumefrenzy.utils.Point;

import costumefrenzy.nedteam.com.costumefrenzy.R;

/**
 * Created by mcat on 7/02/15.
 */
public class testActivity extends Activity {
    private RenderingView view;
    private int statusBarHeight;

    public testActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Get status bar height
        statusBarHeight = getStatusBarHeight();
        //Action bar
        getActionBar().hide();
        //Register events
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
        view = new RenderingView(getBaseContext());
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
                try {
                    Thread.sleep(10000);
                    BitmapUtils.setColorToAll(bitmap4, 0xff000000);
                } catch (InterruptedException e) {}
                view.postInvalidate();
            }
        }).start();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        int action = e.getAction();
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                return onDown(e);
            case MotionEvent.ACTION_UP:
                return onUp(e);
            default:
                return false;
        }
    }

    private Button currentButton = null;
    public boolean onDown(MotionEvent e) {
        //Log.i("DOWN", Float.toString(e.getRawX()) + Float.toString(e.getRawY()));
        Button button = ButtonManager.checkClick(view, (int)e.getRawX(), (int)e.getRawY() - statusBarHeight);
        if(button!=null){
            currentButton = button;
            EventManager.callEvent(new ButtonPressedEvent(button));
        }
        return false;
    }


    public boolean onUp(MotionEvent e) {
        //Log.i("UP", Float.toString(e.getRawX()) + Float.toString(e.getRawY()));
        Button button = ButtonManager.checkClick(view, (int)e.getRawX(), (int)e.getRawY() - statusBarHeight);
        if(button!=null) {
            EventManager.callEvent(new ButtonReleasedEvent(button));
            if(button.equals(currentButton)){
                EventManager.callEvent(new ButtonClickedEvent(button));
            }
        }
        currentButton = null;
        return true;
    }
}
