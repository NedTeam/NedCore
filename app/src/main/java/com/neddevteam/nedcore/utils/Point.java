package com.neddevteam.nedcore.utils;

/**
 * Created by mcat on 8/02/15.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x,int y){

        this.x = x;
        this.y = y;
    }
    public Point(Vector2f vector){
        this.x = (int)vector.getX();
        this.y = (int)vector.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Point p2){
        return x==p2.getX() && y==p2.getY();
    }
}
