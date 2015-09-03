package com.neddevteam.nedcore.physics.shape;

import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;

/**
 * Created by gdefermin on 9/3/15.
 */
public interface Shape {
    Point getCenter();
    void setCenter(Point p);
    BoundingBox getBoundingBox();
    ShapeType getShapeType();
}
