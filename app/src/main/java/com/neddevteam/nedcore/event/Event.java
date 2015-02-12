package com.neddevteam.nedcore.event;

/**
 * Created by gdefermin on 1/31/15.
 */
public abstract class Event {

    private boolean cancelled = false;

    public boolean isCancelled(){
        return cancelled;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }
}
