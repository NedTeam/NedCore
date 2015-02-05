package com.neddevteam.costumefrenzy.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Methods in classes that implement {@link com.neddevteam.costumefrenzy.event.Listener}
 * can handle events using this annotation (Class must be first registered on the event manager
 * with {@link com.neddevteam.costumefrenzy.event.EventManager#register(Class)})
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HandleEvent {
}
