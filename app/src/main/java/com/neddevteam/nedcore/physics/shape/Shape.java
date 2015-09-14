package com.neddevteam.nedcore.physics.shape;

import com.neddevteam.nedcore.physics.Manifold;
import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by gdefermin on 9/3/15.
 */
public interface Shape {
    Vector2f getCenter();
    void setCenter(Vector2f center);
    BoundingBox getBoundingBox();
    ShapeType getShapeType();
    Manifold checkContact(Shape s);
}
