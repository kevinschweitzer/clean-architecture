package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class SaveInstanceObserver extends BusObserver<SaveInstanceObserver.SaveInstance>{
    public SaveInstanceObserver(){super(SaveInstance.class); }

    public static class SaveInstance{

    }
}
