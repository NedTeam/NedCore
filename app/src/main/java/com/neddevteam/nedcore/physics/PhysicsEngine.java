package com.neddevteam.nedcore.physics;

import com.neddevteam.nedcore.event.EventManager;
import com.neddevteam.nedcore.event.predefined.CollisionEvent;
import com.neddevteam.nedcore.math.Graph;
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
        for(GameObject object:w.getObjects()){
            object.getProperties().setLocation(nextLocation(deltaT, object));
            checkAllCollisions2(w);
        }
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


    private static void checkAllCollisions2(World w) {
        for(GameObject obj:w.getObjects()){
            checkEdgeCollision(w, obj);
        }
        Graph<GameObject> collisionGraph = new Graph<>();

        //1º Calculate grid size based on screen properties
        Graph<GameObject> regionsMap = getRegionsMap2(w);
        for(GameObject obj: regionsMap.getVertices()){
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

    private static void checkAllCollisions(World w) {
        for(GameObject obj:w.getObjects()){
            checkEdgeCollision(w, obj);
        }
        Graph<GameObject> collisionGraph = new Graph<>();

        //1º Calculate grid size based on screen properties
        HashMap<Point,List<GameObject>> gridData = getRegionsMap(w);
        //Get objects in same region
        for(Point p:gridData.keySet()){
            List<GameObject> objects = gridData.get(p);
            if(objects.size()!=1){
                //Log.i("NedCore","Possible collision!");
                for(int i=0;i<objects.size()-1;i++){
                    for(int j=i+1;j<objects.size();j++){
                        //Only check once for every pair of objects
                        if(!collisionGraph.edgeExsists(objects.get(i),objects.get(j))) {
                            checkCollision(w,objects.get(i), objects.get(j));
                            collisionGraph.addEdge(objects.get(i),objects.get(j));
                        }
                    }
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


    //Checks if two objects have collided by checking the middle pixel
    //of the intersection of their bounding boxes
    private static void checkCollision(World w,GameObject g1,GameObject g2) {
        BoundingBox intersect = g1.getBoundingBox().intersect(g2.getBoundingBox());
        if(intersect==null)return;
        //Log.i("NedCore",g1.getBoundingBox()+" U "+g2.getBoundingBox()+" = "+intersect.toString());
        Point mid = new Point((intersect.getP1().getX()+intersect.getP2().getX())/2,
                (intersect.getP1().getY()+intersect.getP2().getY())/2);
        Point rel1 = new Point((int)(mid.getX()-g1.getProperties().getLocation().getX()),
                (int)(mid.getY()-g1.getProperties().getLocation().getY()));
        Point rel2 = new Point((int)(mid.getX()-g2.getProperties().getLocation().getX()),
                (int)(mid.getY()-g2.getProperties().getLocation().getY()));
        boolean isG1 = BitmapUtils.checkAlpha(g1.getTexture().getBitmap(),rel1.getX(),rel1.getY());
       boolean isG2 = BitmapUtils.checkAlpha(g2.getTexture().getBitmap(),rel2.getX(),rel2.getY());
        if(isG1 && isG2 && !w.getColliding().edgeExsists(g1,g2)){
            EventManager.callEvent(new CollisionEvent(g1,g2));
            w.getColliding().addEdge(g1,g2);
        }else if(!(isG1 && isG2) && w.getColliding().edgeExsists(g1,g2)){
            w.getColliding().removeEdge(g1,g2);
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
        final int collisionDistance = 500;
        Graph<GameObject> graph = new Graph<>(world.getObjects());
        for(int i=0; i<world.getObjects().size()-1; i++){
            for(int j=i; j<world.getObjects().size(); j++){
                Vector2f location1 = world.getObjects().get(i).getProperties().getLocation();
                Vector2f location2 = world.getObjects().get(j).getProperties().getLocation();
                if(location1.sub(location2).getMod()<=collisionDistance){
                    graph.addEdge(world.getObjects().get(i),world.getObjects().get(j));
                }
            }
        }
        return graph;
    }


}
