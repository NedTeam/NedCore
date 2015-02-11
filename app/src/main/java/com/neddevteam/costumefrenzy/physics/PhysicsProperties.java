package com.neddevteam.costumefrenzy.physics;

import com.neddevteam.costumefrenzy.utils.Point;
import com.neddevteam.costumefrenzy.utils.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcat on 11/02/15.
 */
public class PhysicsProperties {

    private List<Vector2f> forces = new ArrayList<Vector2f>();
    private Point location;
    private Vector2f speed;

    public PhysicsProperties(Point location){
        this.location = location;
    }
}
