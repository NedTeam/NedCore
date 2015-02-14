package com.neddevteam.nedcore.utils;

import android.graphics.Rect;

/**
 * Created by mcat on 8/02/15.
 */
public class BoundingBox {
    private Point p1;
    private Point p2;

    public BoundingBox(Point p1,Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public BoundingBox(Point p1,int width,int height){
        this.p1 = p1;
        this.p2 = new Point(p1.getX()+width,p1.getY()+height);
    }

    public boolean checkInside(int x,int y){
        return x>p1.getX() && x<p2.getX() && y>p1.getY() && y < p2.getY();
    }

    public Point getP1() {
        return p1;
    }
    public Point getP2(){
        return p2;
    }

    public BoundingBox intersect(BoundingBox other) {
        Rect r1 = new Rect(p1.getX(),p1.getY(),p2.getX(),p2.getY());
        Rect r2 = new Rect(other.getP1().getX(),other.getP1().getY(),
                other.getP2().getX(),other.getP2().getY());
        if(!r1.intersect(r2))return null;
        Point pn1 = new Point(Math.max(p1.getX(),other.getP1().getX()),
                Math.max(p1.getY(),other.getP1().getY()));
        Point pn2 = new Point(Math.max(p2.getX(),other.getP2().getX()),
                Math.max(p2.getY(),other.getP2().getY()));
        return new BoundingBox(pn1,pn2);
    }
}
