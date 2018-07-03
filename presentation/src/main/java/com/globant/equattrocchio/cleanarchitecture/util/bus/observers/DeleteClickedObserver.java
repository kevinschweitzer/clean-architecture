package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class DeleteClickedObserver extends BusObserver<DeleteClickedObserver.DeleteClicked> {
    public DeleteClickedObserver(){super(DeleteClicked.class);}

    public static class DeleteClicked{

    }
}
