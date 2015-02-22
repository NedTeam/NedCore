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
    public void onCollide(CollisionEvent event){
        Log.i("NedCore","COLLISION!");
        GameObject obj1 = event.getObj1();
        GameObject obj2 = event.getObj2();
        PhysicsProperties props1 = obj1.getProperties();
        PhysicsProperties props2 = obj2.getProperties();

        float massSum = props1.getMass() + props2.getMass();
        float massDiff = props1.getMass() - props2.getMass();

        Vector2f contactDir = props2.getLocation().sub(props1.getLocation()).normalized();

        /*Velocities are named 'u' if they represent initial velocities (before the collision
        * happened), and 'v' if they are final (after the collision). The x and y axes used
        * here don't line up with the ones the vectors use: variables marked 'x' follow the
        * direction of 'contactDir', while variables marked 'y' are simply perpendicular to
        * that direction.
        *
        * The collision is checked using 1-dimension collision laws with the component of
        * the velocity along the contact direction (hence 'x' and 'y' splitting relative to
        * it). The velocity obtained is then added to the original 'y' component of the
        * velocity ('y' as in perpendicular to 'contactDir') to obtain the final value.*/

        float u1x = props1.getVelocity().dot(contactDir);
        float u2x = props2.getVelocity().dot(contactDir);

        Vector2f u1y = props1.getVelocity().sub(contactDir.multiply(u1x));
        Vector2f u2y = props2.getVelocity().sub(contactDir.multiply(u2x));

        float v1x = (u1x*massDiff + 2*props2.getMass()*u2x)/massSum;
        float v2x = (u2x*massDiff*(-1) + 2*props1.getMass()*u1x)/massSum;

        Vector2f v1 = contactDir.multiply(v1x).add(u1y);
        Vector2f v2 = contactDir.multiply(v2x).add(u2y);

        props1.setVelocity(v1);
        props2.setVelocity(v2);

    }
}
