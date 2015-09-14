package com.neddevteam.nedcore.physics.shape;

import android.util.Log;

import com.neddevteam.nedcore.physics.Manifold;
import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by gdefermin on 9/3/15.
 */
public class Rectangle implements Shape{
    private Vector2f center;
    private final int width;
    private final int height;

    public Rectangle(Vector2f center, int width, int height){
        this.width = width;
        this.height = height;
        this.center = new Vector2f(center);
    }

    @Override
    public Vector2f getCenter() {
        return center;
    }

    @Override
    public void setCenter(Vector2f center) {
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
                new Point((int)center.getX()-width/2, (int)center.getY()-height/2),
                new Point((int)center.getX()+width/2, (int)center.getY()+height/2)
        );
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
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
