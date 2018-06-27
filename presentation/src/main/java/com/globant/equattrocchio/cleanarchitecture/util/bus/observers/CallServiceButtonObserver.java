package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class CallServiceButtonObserver extends BusObserver<CallServiceButtonObserver.CallServiceButtonPressed> {
    public CallServiceButtonObserver() {
        super(CallServiceButtonPressed.class);
    }

    public static class CallServiceButtonPressed {
    }
}