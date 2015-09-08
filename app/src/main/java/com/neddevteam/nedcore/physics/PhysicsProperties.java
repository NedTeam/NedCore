package com.neddevteam.nedcore.physics;

import com.neddevteam.nedcore.utils.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcat on 11/02/15.
 */
public class PhysicsProperties {

    private List<Vector2f> forces = new ArrayList<Vector2f>();
    private Vector2f location;
    private Vector2f velocity;
    private int mass;

    public PhysicsProperties(Vector2f location, int mass){
        this.location = location;
        this.mass = mass;
        velocity = new Vector2f(0,0);
    }

    public List<Vector2f> getForces() {
        return forces;
    }

    public Vector2f getLocation() {
        return location;
    }

    public void setLocation(Vector2f location) {
        this.location = location;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f speed) {
        this.velocity = speed;
    }

    public void addForce(Vector2f force) {
        forces.add(force);
    }

    public void removeForce(Vector2f force) {
        forces.remove(force);
    }

    public float getMass() {
        return mass;
    }

}
