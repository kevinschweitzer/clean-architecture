package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class CountButtonUpBusObserver extends BusObserver<CountButtonUpBusObserver.CountButtonUp> {
    public CountButtonUpBusObserver() {
        super(CountButtonUp.class);
    }

    public static class CountButtonUp {
    }
}