package com.neddevteam.nedcore.physics;

import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by mcat on 11/02/15.
 */
public class PhysicsEngine {

    public static void calculatePhysics(World w){
        long t0 = w.getLastUpdated();
        long t = System.currentTimeMillis();
        long deltaT = Math.abs(t-t0);
        for(GameObject go:w.getObjects()){
            PhysicsProperties props = go.getProperties();
            //Calculate velocity based on forces
            //Acceleration is in PIXELS/MS²
            //F=M*A, so forces are in kG*PIXELS/MS²
            Vector2f fTotal = new Vector2f(0,0);
            for(Vector2f force:props.getForces()){
                fTotal = fTotal.add(force);
            }
            Vector2f acceleration = fTotal.divide(props.getMass());
            props.setVelocity(props.getVelocity().add(acceleration.multiply(deltaT)));
            //S=m*v
            //Calculate movement based on velocity
            //VELOCITY IS IN PIXELS/MS!!!!!!!!!!!!!!!
            Vector2f vel = props.getVelocity();
            props.setLocation(props.getLocation().add(vel.multiply(deltaT)));
        }
    }
}
