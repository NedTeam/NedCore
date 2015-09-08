package com.neddevteam.nedcore.physics.shape;

import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by gdefermin on 9/3/15.
 */
public class Circle implements Shape{
    private Vector2f center;
    private final int radius;

    public Circle(Vector2f center, int radius){
        this.center = new Vector2f(center);
        this.radius = radius;
    }

    @Override
    public Vector2f getCenter() {
        return center;
    }

    @Override
    public void setCenter(Vector2f center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public BoundingBox getBoundingBox() {
        int x = (int) center.getX();
        int y = (int) center.getY();
        return new BoundingBox(new Point(x-radius, y-radius), new Point(x+radius, y+radius));
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.CIRCLE;
    }

}
