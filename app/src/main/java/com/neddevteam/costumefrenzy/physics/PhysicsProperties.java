package com.neddevteam.costumefrenzy.physics;

import com.neddevteam.costumefrenzy.utils.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcat on 11/02/15.
 */
public class PhysicsProperties {

    private List<Vector2f> forces = new ArrayList<Vector2f>();
    private Vector2f location;
    private Vector2f velocity;
    private float mass;

    public PhysicsProperties(Vector2f location, float mass){
        this.location = location;
        this.mass = mass;
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

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public List<Vector2f> getForces() {
        return forces;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
