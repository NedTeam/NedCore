package com.neddevteam.costumefrenzy.utils;

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
}
