package com.neddevteam.nedcore.physics;

import android.graphics.Bitmap;

import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by mcat on 11/02/15.
 */
public class GameObject {
    private Bitmap texture;
    private PhysicsProperties properties;

    public GameObject(Bitmap texture, PhysicsProperties properties){
        this.texture = texture;
        this.properties = properties;
    }

    public Bitmap getTexture() {
        return texture;
    }

    public PhysicsProperties getProperties() {
        return properties;
    }

    public void applyForce(Vector2f force){
        properties.addForce(force);
    }
}

