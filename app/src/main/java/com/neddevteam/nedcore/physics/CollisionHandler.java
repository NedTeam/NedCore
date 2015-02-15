package com.neddevteam.nedcore.physics;

import android.util.Log;

import com.neddevteam.nedcore.event.EventPriority;
import com.neddevteam.nedcore.event.HandleEvent;
import com.neddevteam.nedcore.event.Listener;
import com.neddevteam.nedcore.event.predefined.CollisionEvent;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by mcat on 15/02/15.
 */
public class CollisionHandler implements Listener {

    @HandleEvent(priority=EventPriority.VERY_LOW)
    public void collide(CollisionEvent event){
        Log.i("NedCore","COLLISION!");
        GameObject obj1 = event.getObj1();
        GameObject obj2 = event.getObj2();
        PhysicsProperties props1 = obj1.getProperties();
        PhysicsProperties props2 = obj2.getProperties();
        float v1 = (float)(props2.getVelocity().getMod()*props2.getMass())/props1.getMass();
        float v2 = (float)(props1.getVelocity().getMod()*props1.getMass())/props2.getMass();
        Vector2f dir2 = props2.getLocation().sub(props1.getLocation()).normalized();
        Vector2f dir1 = dir2.multiply(-1);
        props1.setVelocity(dir1.multiply(v1));
        props2.setVelocity(dir2.multiply(v2));
    }
}
