package com.neddevteam.nedcore.physics.shape;

import android.util.Log;

import com.neddevteam.nedcore.physics.Manifold;
import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by gdefermin on 9/3/15.
 */
public class Triangle implements Shape{
    private final Point[] vertices;
    private Vector2f center;

    /**
     * Creates a triangle from 3 vertices and a center.
     * The vertices' coordinates are relative to the center and can be negative.
     *
     * @param vertices an array of 3 vertices
     * @param center center of the triangle
     */
    public Triangle(Vector2f center, Point[] vertices) {
        this.vertices = new Point[3];
        for(int i=0; i<3; i++) {
            this.vertices[i] = new Point(vertices[i]);
        }
        this.center = new Vector2f(center);
    }

    public Point[] getVertices() {
        return vertices;
    }

    @Override
    public Vector2f getCenter() {
        return center;
    }

    @Override
    public void setCenter(Vector2f center) {
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
            absoluteVertices[i] = new Point((int)center.getX() + vertices[i].getX(), (int)center.getY() + vertices[i].getY());
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

    @Override
    public Manifold checkContact(Shape s) {
        Manifold manifold = null;

        switch(s.getShapeType()){
            case CIRCLE:

                break;
            case TRIANGLE:

                break;
            case RECTANGLE:

                break;
            default:
                Log.e("NedCore", "Unknown shape");
        }
        return manifold;
    }
}
