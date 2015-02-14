package com.neddevteam.nedcore.physics;

import android.util.Log;

import com.neddevteam.nedcore.layer.RenderingLayer;
import com.neddevteam.nedcore.render.RenderingView;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mcat on 11/02/15.
 */
public class World {

    private Long lastUpdate;
    private Vector2f gravity;
    private List<GameObject> objects = new ArrayList<GameObject>();
    private Thread physicsThread;
    private RenderingLayer container;
    private HashMap<GameObject,Vector2f> positions = new HashMap<>();
    private int xDivisions;
    private int yDivisions;

    public World(Vector2f gravity, final RenderingLayer container, final RenderingView view,
                 int wScreen,int hScreen){
        this.container = container;
        final World w = this;
        this.gravity = gravity;
        xDivisions = (wScreen/50)+1;
        yDivisions = (hScreen/50)+1;
        Log.i("NedCore",wScreen+"<->"+hScreen+"="+xDivisions+"&"+yDivisions);
        physicsThread = new Thread(new Runnable(){
            @Override
            public void run() {
                lastUpdate = System.currentTimeMillis();
                while(!Thread.interrupted()){
                    PhysicsEngine.calculatePhysics(w);
                    for(GameObject object:objects){
                        Vector2f pos0 = positions.get(object);
                        Vector2f pos = object.getProperties().getLocation();
                        container.moveBitmap(new Point(pos0),new Point(pos));
                        positions.put(object,pos);
                    }
                    lastUpdate = System.currentTimeMillis();
                    view.postInvalidate();
                    try {
                        Thread.sleep(32);
                    } catch (InterruptedException e) {}
                }
            }
        });
        physicsThread.start();
    }

    public long getLastUpdated() {
        return lastUpdate;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public void addObject(GameObject object){
        objects.add(object);
        positions.put(object,object.getProperties().getLocation());
    }

    public int getxDivisions(){return xDivisions;}

    public int getyDivisions(){return yDivisions;}
}
