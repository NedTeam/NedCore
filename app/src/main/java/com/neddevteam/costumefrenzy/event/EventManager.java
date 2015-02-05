package com.neddevteam.costumefrenzy.event;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gdefermin on 1/31/15.
 */
public class EventManager {
    private HashMap<Class, List<Method>> registeredMethods;

    public EventManager() {
        registeredMethods = new HashMap<>();
    }

    /**
     * Adds {@code cls} to the list of classes that can define events
     *
     * @param cls class to be registered
     */
    public void register(Class cls) {
        //Check that class implements Listener and isn't already registered
        if(!Listener.class.isAssignableFrom(cls) || registeredMethods.containsKey(cls))
            return;

        Method[] methods = cls.getMethods();
        for(Method method: methods){
            //Make sure method has HandleEvent annotation
            if (method.getAnnotation(HandleEvent.class) != null){
                if(!registeredMethods.containsKey(cls))
                    registeredMethods.put(cls, new ArrayList<Method>());

                registeredMethods.get(cls).add(method);
            }
        }
    }

    public void callEvent(final Event event){
        new Thread(){
            @Override
            public void run(){
                for(Map.Entry<Class, List<Method>> entry: registeredMethods.entrySet()){
                    for(Method method: entry.getValue()){
                        try {
                            method.invoke(entry.getKey().newInstance(), event);
                        } catch (InstantiationException e) {
                            Log.e("CostumeFrenzy", e.toString());
                        } catch (IllegalAccessException e) {
                            Log.e("CostumeFrenzy", e.toString());
                        } catch (InvocationTargetException e) {
                            Log.e("CostumeFrenzy", e.toString());
                        }
                    }
                }
            }
        }.start();
    }
}
