package com.neddevteam.nedcore.physics;

import android.util.Log;

import com.neddevteam.nedcore.event.EventManager;
import com.neddevteam.nedcore.event.predefined.CollisionEvent;
import com.neddevteam.nedcore.math.Graph;
import com.neddevteam.nedcore.physics.shape.Circle;
import com.neddevteam.nedcore.physics.shape.Shape;
import com.neddevteam.nedcore.physics.shape.ShapeType;
import com.neddevteam.nedcore.utils.BitmapUtils;
import com.neddevteam.nedcore.utils.BoundingBox;
import com.neddevteam.nedcore.utils.Point;
import com.neddevteam.nedcore.utils.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mcat on 11/02/15.
 */
public class PhysicsEngine {

    public static void calculatePhysics(World w){
        long t0 = w.getLastUpdated();
        long t = System.currentTimeMillis();
        long deltaT = Math.abs(t - t0);
        //long deltaT = 50;

        checkAllCollisions(w);

        for(GameObject obj: w.getObjects())
            obj.getProperties().setLocation(nextLocation(deltaT, obj));

    }

    private static Vector2f nextLocation(long deltaT, GameObject object) {
        PhysicsProperties props = object.getProperties();
        //Calculate velocity based on forces
        //Acceleration is in PIXELS/MS²
        //F=M*A, so forces are in kG*PIXELS/MS²
        Vector2f fTotal = new Vector2f(0,0);
        for(Vector2f force:props.getForces()){
            fTotal = fTotal.add(force);
        }
        Vector2f acceleration = fTotal.divide(props.getMass());
        props.setVelocity(props.getVelocity().add(acceleration.multiply(deltaT)));
        //S=m*v
        //Calculate movement based on velocity
        //VELOCITY IS IN PIXELS/MS!!!!!!!!!!!!!!!
        Vector2f vel = props.getVelocity();
        return props.getLocation().add(vel.multiply(deltaT));
    }


    private static void checkAllCollisions(World w) {
        Graph<GameObject> collisionGraph = new Graph<>();

        //1º Calculate grid size based on screen properties
        Graph<GameObject> regionsMap = getRegionsMap2(w);
        for(GameObject obj: regionsMap.getVertices()){
            checkEdgeCollision(w, obj);

            //Get objects in same region
            List<GameObject> objects = regionsMap.getRelations(obj);

            //Log.i("NedCore","Possible collision!");
            for(GameObject obj2: objects){
                //Only check once for every pair of objects
                if(!collisionGraph.edgeExsists(obj,obj2)) {
                    checkCollision(w,obj, obj2);
                    collisionGraph.addEdge(obj,obj2);
                }
            }
        }
    }


    private static void checkEdgeCollision(World w, GameObject obj) {
        //Check collisions with screen edges
        PhysicsProperties props  = obj.getProperties();
        BoundingBox box = obj.getBoundingBox();
        Vector2f vel = props.getVelocity();
        if( vel.getX()<0 && box.getP1().getX() <= 0){
            Vector2f newVel = new Vector2f(-vel.getX(), vel.getY());
            props.setVelocity(newVel);
            props.setLocation(new Vector2f(0,props.getLocation().getY()));
            //TODO left edge collision
        } else if( vel.getY()<0 && box.getP1().getY() <= 0){
            Vector2f newVel = new Vector2f(vel.getX()*2/3, -vel.getY()*2/3);
            props.setVelocity(newVel);
            props.setLocation(new Vector2f(props.getLocation().getX(),0));
            //TODO top edge collision
        } else if( vel.getX()>0 && box.getP2().getX() >= w.getScreenWidth()){
            Vector2f newVel = new Vector2f(-vel.getX()*2/3, vel.getY());
            props.setVelocity(newVel);
            int wObject = box.getP2().getX()-box.getP1().getX();
            props.setLocation(new Vector2f(w.getScreenWidth()-wObject,
                    props.getLocation().getY()));
            //TODO right edge collision
        } else if( vel.getY()>0 && box.getP2().getY() >= w.getScreenHeight()){
            Vector2f newVel = new Vector2f(vel.getX(), -vel.getY()*2/3);
            props.setVelocity(newVel);
            int hObject = box.getP2().getY()-box.getP1().getY();
            props.setLocation(new Vector2f(props.getLocation().getX(),
                    w.getScreenHeight()-hObject));
            //TODO bottom edge collision
        }
    }


    /* Checks if two objects are colliding (narrow phase)*/
    private static void checkCollision(World w,GameObject g1,GameObject g2) {
        // First check if their bounding boxes are intersecting
        BoundingBox intersect = g1.getBoundingBox().intersect(g2.getBoundingBox());
        if(intersect==null)
            return; //Objects can't be intersecting

        Manifold manifold = checkContact(
                g1.getProperties().getShape(),
                g2.getProperties().getShape()
        );

        if(manifold != null){
            // 1. Adjust position
            //TODO: non-circles

            Circle g1Shape = (Circle) g1.getProperties().getShape();
            Circle g2Shape = (Circle) g2.getProperties().getShape();

            if(g1.getProperties().getLocation().getX() > manifold.getPoint().getX() ||
               g1.getProperties().getLocation().getY() > manifold.getPoint().getY()  ) {

                g1.getProperties().setLocation(
                        manifold.getPoint().add(manifold.getNormal().multiply(g1Shape.getRadius()))
                );

                g2.getProperties().setLocation(
                        manifold.getPoint().sub(manifold.getNormal().multiply(g2Shape.getRadius()))
                );
            } else {
                g1.getProperties().setLocation(
                        manifold.getPoint().sub(manifold.getNormal().multiply(g1Shape.getRadius()))
                );

                g2.getProperties().setLocation(
                        manifold.getPoint().add(manifold.getNormal().multiply(g2Shape.getRadius()))
                );
            }

            // 2. Resolve collision if necessary
            if(!w.getColliding().edgeExsists(g1,g2)) {
                EventManager.callEvent(new CollisionEvent(g1, g2));
                w.getColliding().addEdge(g1, g2);
            }
        } else if(w.getColliding().edgeExsists(g1,g2)){
            w.getColliding().removeEdge(g1, g2);
        }
    }

    /**
     * Checks if two shapes are colliding
     *
     * @param s1
     * @param s2
     * @return The collision manifold of two shapes or null if not colliding
     */
    private static Manifold checkContact(Shape s1, Shape s2) {
        Manifold manifold = null;
        Vector2f point = null;
        Vector2f normal = null;

        switch (s1.getShapeType()) {
            case CIRCLE:
                Circle c1 = (Circle) s1;
                switch (s2.getShapeType()){
                    case CIRCLE:
                        Circle c2 = (Circle) s2;

                        int radSum = (c1.getRadius() + c2.getRadius());

                        normal = s1.getCenter().sub(s2.getCenter());

                        double distance = normal.getMod();

                        if(radSum >= distance) {
                            point = new Vector2f(
                                    (c1.getCenter().getX()+c2.getCenter().getX())/2,
                                    (c1.getCenter().getY()+c2.getCenter().getY())/2
                            );
                            manifold = new Manifold(point, normal);
                        }

                }
                break;
            case RECTANGLE:

                break;
            case TRIANGLE:

                break;
            default:
                Log.e("NedCore", "Unknown shape");
        }
        return manifold;
    }

    //Checks which object is in which subdivision of the World
    private static HashMap<Point,List<GameObject>> getRegionsMap(World w){
        HashMap<Point,List<GameObject>> points = new HashMap<>();
        for(GameObject go:w.getObjects()){
            Point p1 = go.getBoundingBox().getP1();
            int p1x = p1.getX()/50;
            int p1y = p1.getY()/50;
            Point p2 = go.getBoundingBox().getP2();
            int p2x = p2.getX()/50;
            int p2y = p2.getY()/50;
            //Log.i("NedCore",p1x+","+p1y+"-"+p2x+","+p2y);
            for(int i=p1x;i<=p2x;i++) {
                for (int j = p1y; j <= p2y; j++) {
                    Point p = new Point(i, j);
                    if (points.containsKey(p)) {
                        points.get(p).add(go);
                    } else {
                        List<GameObject> objects = new ArrayList<>();
                        objects.add(go);
                        points.put(p, objects);

                    }
                }
            }
        }
        return points;
    }


    private static Graph<GameObject> getRegionsMap2(World world){
        /* TODO:
         *  Instead of an absolute value, this should depend on the
         *  average size of objects in the world (or maximum)
         */
        final int collisionDistance = 300;

        Graph<GameObject> graph = new Graph<>(world.getObjects());
        for(int i=0; i<world.getObjects().size()-1; i++){
            for(int j=i; j<world.getObjects().size(); j++){
                GameObject obj1 = world.getObjects().get(i);
                GameObject obj2 = world.getObjects().get(j);
                Vector2f[] obj1points = {
                        obj1.getProperties().getLocation(),
                        new Vector2f(obj1.getBoundingBox().getP2().getX(),obj1.getBoundingBox().getP1().getY()),
                        new Vector2f(obj1.getBoundingBox().getP1().getX(),obj1.getBoundingBox().getP2().getY()),
                        new Vector2f(obj1.getBoundingBox().getP2().getX(),obj1.getBoundingBox().getP2().getY()),
                };
                Vector2f[] obj2points = {
                        obj2.getProperties().getLocation(),
                        new Vector2f(obj2.getBoundingBox().getP2().getX(),obj2.getBoundingBox().getP1().getY()),
                        new Vector2f(obj2.getBoundingBox().getP1().getX(),obj2.getBoundingBox().getP2().getY()),
                        new Vector2f(obj2.getBoundingBox().getP2().getX(),obj2.getBoundingBox().getP2().getY()),
                };
                for(Vector2f point1: obj1points){
                    for(Vector2f point2: obj2points){
                        if(point1.sub(point2).getMod()<=collisionDistance){
                            graph.addEdge(world.getObjects().get(i),world.getObjects().get(j));
                        }
                    }
                }
            }
        }
        return graph;
    }


}
