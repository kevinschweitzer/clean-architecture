package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class StringBusObserver extends BusObserver<String> {
    public StringBusObserver() {
        super(String.class);
    }
}
