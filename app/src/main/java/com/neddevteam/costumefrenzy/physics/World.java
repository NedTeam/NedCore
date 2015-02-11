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

    public World(Vector2f gravity){
        this.gravity = gravity;
        //TODO Start physics thread
    }
}
