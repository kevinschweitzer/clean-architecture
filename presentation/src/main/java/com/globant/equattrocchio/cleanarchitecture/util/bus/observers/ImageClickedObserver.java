package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class ImageClickedObserver extends BusObserver<ImageClickedObserver.ImageClicked> {
    public ImageClickedObserver(){ super(ImageClicked.class); }

    public static class ImageClicked{

        private long id;

        public ImageClicked(long id){
            this.id = id;
        }

        public long getId(){
            return id;
        }
    }
}
