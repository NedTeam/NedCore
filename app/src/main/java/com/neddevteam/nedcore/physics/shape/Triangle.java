package com.neddevteam.nedcore.physics.shape;

import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;

/**
 * Created by gdefermin on 9/3/15.
 */
public class Triangle implements Shape{
    private final Point[] vertices;
    private Point center;

    /**
     * Creates a triangle from 3 vertices and a center.
     * The vertices' coordinates are relative to the center and can be negative.
     *
     * @param vertices an array of 3 vertices
     * @param center center of the triangle
     */
    public Triangle(Point[] vertices, Point center) {
        this.vertices = new Point[3];
        for(int i=0; i<3; i++) {
            this.vertices[i] = new Point(vertices[i]);
        }
        this.center = new Point(center);
    }

    public Point[] getVertices() {
        return vertices;
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public BoundingBox getBoundingBox() {
        Point[] absoluteVertices = new Point[3];
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            absoluteVertices[i] = new Point(center.getX() + vertices[i].getX(), center.getY() + vertices[i].getY());
            if(absoluteVertices[i].getX() < minX)
                minX = absoluteVertices[i].getX();
            if(absoluteVertices[i].getY() < minY)
                minY = absoluteVertices[i].getY();
            if(absoluteVertices[i].getX() > maxX)
                maxX = absoluteVertices[i].getX();
            if(absoluteVertices[i].getY() > maxY)
                maxY = absoluteVertices[i].getY();
        }

        return new BoundingBox(new Point(minX, minY), new Point(maxX, maxY));
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.TRIANGLE;
    }
}
