package com.neddevteam.nedcore.physics.shape;

import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;

/**
 * Created by gdefermin on 9/3/15.
 */
public class Circle implements Shape{
    private Point center;
    private final int radius;

    public Circle(int x, int y, int radius){
        center = new Point(x,y);
        this.radius = radius;
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public void setCenter(Point center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public BoundingBox getBoundingBox() {
        int x = center.getX();
        int y = center.getY();
        return new BoundingBox(new Point(x-radius, y-radius), new Point(x+radius, y+radius));
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.CIRCLE;
    }

}
