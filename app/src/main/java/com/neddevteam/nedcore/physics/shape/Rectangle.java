package com.neddevteam.nedcore.physics.shape;

import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;

/**
 * Created by gdefermin on 9/3/15.
 */
public class Rectangle implements Shape{
    private Point center;
    private final int width;
    private final int height;

    public Rectangle(int width, int height, Point center){
        this.width = width;
        this.height = height;
        this.center = new Point(center);
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public void setCenter(Point center) {
        this.center = center;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(
                new Point(center.getX()-width/2,center.getY()-height/2),
                new Point(center.getX()+width/2,center.getY()+height/2)
        );
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
    }
}
