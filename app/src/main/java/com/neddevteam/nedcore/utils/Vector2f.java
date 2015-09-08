package com.neddevteam.nedcore.utils;

/**
 * Created by mcat on 11/02/15.
 */
public class Vector2f {

    private final float x;
    private final float y;

    public Vector2f(float x,float y){
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public double getMod() {
        return Math.sqrt(x*x+y*y); // sqrt() is a very complicated operation, we should
                                   // consider approximating it
    }

    public Vector2f divide(float value){
        return new Vector2f(x/value,y/value);
    }

    public Vector2f multiply(float value){
        return new Vector2f(x*value,y*value);
    }

    public Vector2f add(Vector2f add) {
        return new Vector2f(x+add.getX(),y+add.getY());
    }

    public Vector2f sub(Vector2f sub) {
        return new Vector2f(x-sub.getX(),+y-sub.getY());
    }

    public Vector2f normalized() {
        return divide((float)getMod());
    }

    public float dot(Vector2f v2) {
        return x*v2.getX() + y*v2.getY();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || !(o instanceof Vector2f))
            return false;

        Vector2f v = (Vector2f) o;
        return x == v.getX() && y == v.getY();
    }

    @Override
    public int hashCode(){
        return (int)(100*x + 100*y);
    }
}
