package com.neddevteam.nedcore.event.predefined;

import com.neddevteam.nedcore.event.Event;
import com.neddevteam.nedcore.physics.GameObject;
import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by gdefermin on 2/14/15.
 */
public class CollisionEvent extends Event{
    private GameObject obj1;
    private GameObject obj2;
    private ScreenEdge edge;

    public enum ScreenEdge {LEFT, DOWN, RIGHT, UP}

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
     * @param edge
     */
    public CollisionEvent(GameObject obj1, ScreenEdge edge){
        this.obj1 = obj1;
        this.edge = edge;
    }

    /**
     * If this is null, the collision is with an object
     * @return
     */
    public ScreenEdge getEdge() { return edge; }

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
