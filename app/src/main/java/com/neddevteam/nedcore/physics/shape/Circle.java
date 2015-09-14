package com.neddevteam.nedcore.physics.shape;

import android.util.Log;

import com.neddevteam.nedcore.physics.Manifold;
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

    @Override
    public Manifold checkContact(Shape s) {
        Manifold manifold = null;
        Vector2f normal;
        Vector2f point;

        switch(s.getShapeType()){
            case CIRCLE:
                Circle c = (Circle) s;

                int radSum = (getRadius() + c.getRadius());

                normal = getCenter().sub(s.getCenter());

                double distance = normal.getMod();

                if(radSum >= distance) {
                    point = new Vector2f(
                            (c.getCenter().getX()+c.getCenter().getX())/2,
                            (c.getCenter().getY()+c.getCenter().getY())/2
                    );
                    manifold = new Manifold(point, normal);
                }
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
