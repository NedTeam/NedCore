package com.neddevteam.nedcore.physics;

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
        long deltaT = Math.abs(t-t0);
        for(GameObject go:w.getObjects()){
            PhysicsProperties props = go.getProperties();
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
            props.setLocation(props.getLocation().add(vel.multiply(deltaT)));
            //TODO Check colisions between objects WIP
            // checkAllCollisions(w);
        }
    }

    private static void checkAllCollisions(World w) {
        //1º Calculate grid size based on screen properties
        HashMap<Point,List<GameObject>> gridData = getRegionsMap(w);
        //Get objects in same region
        for(Point p:gridData.keySet()){
            List<GameObject> objects = gridData.get(p);
            if(objects.size()!=1){
                for(int i=0;i<objects.size()-1;i++){
                    for(int j=i+1;j<objects.size();j++){
                        checkCollision(objects.get(i),objects.get(j));
                    }

                    //Check collisions with screen edges
                    if(objects.get(i).getBoundingBox().getP1().getX() <= 0){
                        //TODO left edge collision
                    } else if(objects.get(i).getBoundingBox().getP1().getY() <= 0){
                        //TODO top edge collision
                    } else if(objects.get(i).getBoundingBox().getP2().getX() >= w.getScreenWidth()){
                        //TODO right edge collision
                    } else if(objects.get(i).getBoundingBox().getP2().getY() >= w.getScreenHeight()){
                        //TODO bottom edge collision
                    }
                }
            }
        }
    }


    //Checks if two objects have collided by checking the middle pixel
    //of the intersection of their bounding boxes
    private static void checkCollision(GameObject g1,GameObject g2) {
        BoundingBox intersect = g1.getBoundingBox().intersect(g2.getBoundingBox());
        Point mid = new Point((intersect.getP1().getX()+intersect.getP2().getX())/2,
                (intersect.getP1().getY()+intersect.getP2().getY())/2);
        Point rel1 = new Point((int)(mid.getX()-g1.getProperties().getLocation().getX()),
                (int)(mid.getY()-g1.getProperties().getLocation().getY()));
        Point rel2 = new Point((int)(mid.getX()-g2.getProperties().getLocation().getX()),
                (int)(mid.getY()-g2.getProperties().getLocation().getY()));
        boolean isG1 = BitmapUtils.checkAlpha(g1.getTexture(),rel1.getX(),rel1.getY());
        boolean isG2 = BitmapUtils.checkAlpha(g2.getTexture(),rel2.getX(),rel2.getY());
        if(isG1 && isG2){
            //TODO EVENT
        }
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



}
