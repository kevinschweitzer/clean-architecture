package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class RefreshClickedObserver extends BusObserver<RefreshClickedObserver.RefreshClicked>{
    public RefreshClickedObserver(){ super(RefreshClicked.class); }

    public static class RefreshClicked{

    }
}
