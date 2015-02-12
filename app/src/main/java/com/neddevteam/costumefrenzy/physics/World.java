package com.neddevteam.costumefrenzy.physics;

import com.neddevteam.costumefrenzy.utils.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcat on 11/02/15.
 */
public class World {

    private Long lastUpdate;
    private Vector2f gravity;
    private List<GameObject> objects = new ArrayList<GameObject>();
    private Thread physicsThread;

    public World(Vector2f gravity){
        final World w = this;
        this.gravity = gravity;
        physicsThread = new Thread(new Runnable(){
            @Override
            public void run() {
                while(!Thread.interrupted()){
                    PhysicsEngine.calculatePhysics(w);
                    try {
                        Thread.sleep(100);
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
}
