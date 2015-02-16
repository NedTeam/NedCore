package com.neddevteam.nedcore.event.predefined;

import com.neddevteam.nedcore.event.Event;
import com.neddevteam.nedcore.physics.GameObject;

/**
 * Created by gdefermin on 2/14/15.
 */
public class CollisionEvent extends Event{
    private GameObject obj1;
    private GameObject obj2;


    /**
     * Called every time two objects collide
     * @param obj1
     * @param obj2
     */
    public CollisionEvent(GameObject obj1, GameObject obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    /**
     * Called every time an object collides with the screen edge
     * @param obj1
     */
    public CollisionEvent(GameObject obj1){
        this.obj1 = obj1;
    }

    /**
     * If this is null, the collision is with the screen edge
     * @return
     */
    public GameObject getObj2() {
        return obj2;
    }

    public GameObject getObj1() {
        return obj1;
    }

}
