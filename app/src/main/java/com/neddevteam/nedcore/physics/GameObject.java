package com.neddevteam.nedcore.physics;

import com.neddevteam.nedcore.render.BitmapDrawable;
import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by mcat on 11/02/15.
 */
public class GameObject {
    private BitmapDrawable texture;
    private PhysicsProperties properties;

    public GameObject(BitmapDrawable texture, PhysicsProperties properties){
        this.texture = texture;
        this.properties = properties;
    }

    public BitmapDrawable getTexture() {
        return texture;
    }

    public PhysicsProperties getProperties() {
        return properties;
    }

    public void applyForce(Vector2f force){
        properties.addForce(force);
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(new Point(properties.getLocation()),
                texture.getBitmap().getWidth(),texture.getBitmap().getHeight());
    }

}

