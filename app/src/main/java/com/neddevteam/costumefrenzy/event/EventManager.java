package com.neddevteam.costumefrenzy.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdefermin on 1/31/15.
 */
public class EventManager {
    private List<Listener> registeredListeners;

    public EventManager() {
        registeredListeners = new ArrayList<Listener>();
    }

    /**
     * Adds {@code listener} to the list of classes that can define events
     *
     * @param listener
     */
    public void register(Listener listener) {
        if(!registeredListeners.contains(listener)) {
            registeredListeners.add(listener);

            Method[] methods = listener.getClass().getMethods();
            for(Method method: methods){
                if (method.getAnnotation(HandleEvent.class) != null){
                    //TODO
                }
            }
        }
    }

    public void callEvent(Event event){
        //TODO
    }
}
