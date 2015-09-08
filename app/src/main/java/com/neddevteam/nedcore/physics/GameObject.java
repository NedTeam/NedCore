package com.neddevteam.nedcore.physics;

import com.neddevteam.nedcore.physics.shape.Shape;
import com.neddevteam.nedcore.render.BitmapDrawable;
import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

import java.util.Objects;

/**
 * Created by mcat on 11/02/15.
 */
public class GameObject {
    private BitmapDrawable texture;
    private PhysicsProperties properties;
    private Shape shape;
    private static int nObjects;


    private int id;

    public GameObject(BitmapDrawable texture, PhysicsProperties properties, Shape shape){
        this.shape = shape; //Temporary
        this.texture = texture;
        this.properties = properties;
        id = nObjects++;
    }

    public BitmapDrawable getTexture() {
        return texture;
    }

    public PhysicsProperties getProperties() {
        return properties;
    }

    public void setLocation(Vector2f location) {
        properties.setLocation(location);
        shape.setCenter(location);
    }

    public void applyForce(Vector2f force){
        properties.addForce(force);
    }

    public BoundingBox getBoundingBox() {
        return new BoundingBox(new Point(properties.getLocation()),
                texture.getBitmap().getWidth(),texture.getBitmap().getHeight());
    }

    public int getId() {
        return id;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof GameObject))
            return false;
        if(o == this)
            return true;

        GameObject go = (GameObject) o;
        return id == go.getId();
    }

    @Override
    public int hashCode() {
        return 11*id;
    }

}

