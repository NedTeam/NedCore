package com.neddevteam.costumefrenzy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.neddevteam.costumefrenzy.button.Button;
import com.neddevteam.costumefrenzy.button.ButtonManager;
import com.neddevteam.costumefrenzy.event.EventManager;
import com.neddevteam.costumefrenzy.event.predefined.ButtonClickedEvent;
import com.neddevteam.costumefrenzy.event.predefined.ButtonPressedEvent;
import com.neddevteam.costumefrenzy.event.predefined.ButtonReleasedEvent;
import com.neddevteam.costumefrenzy.render.RenderingView;

/**
 * To create a custom activity, extend this class and override {@link #onCreate(android.os.Bundle)}<br>
 *     Add layers to the default view (obtained by {@link #getView()}) to draw elements on screen.
 */
public class GameActivity extends Activity {

    private RenderingView view;
    private int statusBarHeight;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        statusBarHeight = getStatusBarHeight();
        view = new RenderingView(getBaseContext());
        //Register events
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
        Button button = ButtonManager.checkClick(view, (int) e.getRawX(), (int) e.getRawY() - statusBarHeight);
        if(button!=null){
            currentButton = button;
            EventManager.callEvent(new ButtonPressedEvent(button));
        }
        return false;
    }


    public boolean onUp(MotionEvent e) {
        //Log.i("UP", Float.toString(e.getRawX()) + Float.toString(e.getRawY()));
        Button button = ButtonManager.checkClick(view, (int) e.getRawX(), (int) e.getRawY() - statusBarHeight);
        if(button!=null) {
            EventManager.callEvent(new ButtonReleasedEvent(button));
            if(button.equals(currentButton)){
                EventManager.callEvent(new ButtonClickedEvent(button));
            }
        }
        currentButton = null;
        return true;
    }

    /**
     * Returns the view that is used to draw elements in screen.<br>
     *     To do so, add layers to it using {@link com.neddevteam.costumefrenzy.render.RenderingView#addLayer(com.neddevteam.costumefrenzy.layer.Layer)}
     * @return view
     */
    public RenderingView getView(){
         return view;
    }
}
