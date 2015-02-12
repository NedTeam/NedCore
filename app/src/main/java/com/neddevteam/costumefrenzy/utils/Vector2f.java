package com.neddevteam.costumefrenzy.utils;

/**
 * Created by mcat on 11/02/15.
 */
public class Vector2f {

    private float x;
    private float y;

    public Vector2f(float x,float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double getMod() {
        return Math.sqrt(x*x+y*y);
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
}
