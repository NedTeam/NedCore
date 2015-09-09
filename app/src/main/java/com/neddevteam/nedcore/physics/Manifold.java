package com.neddevteam.nedcore.physics;

import com.neddevteam.nedcore.utils.Vector2f;

/**
 * Created by gdefermin on 9/9/15.
 */
public class Manifold {

    private final Vector2f point;
    private final Vector2f normal;

    public Manifold(Vector2f point, Vector2f normal) {
        this.point = new Vector2f(point);
        this.normal = (new Vector2f(normal)).normalized();
    }

    public Vector2f getPoint() {
        return point;
    }

    /**
     *
     * @return A normalized vector with the direction of the collision normal
     */
    public Vector2f getNormal() {
        return normal;
    }

}
