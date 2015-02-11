package com.neddevteam.nedcore.event;

import android.util.Log;

import com.neddevteam.nedcore.utils.CollectionUtils;

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
    private static HashMap<Class, List<Method>> registeredMethods = new HashMap<Class,List<Method>>();


    /**
     * Adds {@code cls} to the list of classes that can define events
     *
     * @param cls class to be registered
     */
    public static void register(Class cls) {
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

    public static void callEvent(final Event event){
        new Thread(){
            @Override
            public void run(){
                List<MethodWrapper> methods = new ArrayList<MethodWrapper>();
                for(Map.Entry<Class, List<Method>> entry: registeredMethods.entrySet()){
                    for(Method method: entry.getValue()){
                        if(method.getParameterTypes().length==1 &&
                                method.getParameterTypes()[0].equals(event.getClass())){
                            EventPriority priority = method.getAnnotation(HandleEvent.class).priority();
                            CollectionUtils.sortedInsertion(methods,new MethodWrapperComparator(),
                                    new MethodWrapper(method,priority));
                        }
                    }
                }
                for(int i=methods.size()-1;i>=0 && !event.isCancelled();i--){
                    Method m = methods.get(i).getM();
                    try {
                        m.invoke(m.getDeclaringClass().newInstance(), event);
                    } catch (InstantiationException e) {
                        Log.e("CostumeFrenzy", e.toString());
                    } catch (IllegalAccessException e) {
                        Log.e("CostumeFrenzy", e.toString());
                    } catch (InvocationTargetException e) {
                        Log.e("CostumeFrenzy", e.toString());
                    }

                }
            }
        }.start();
    }
}
