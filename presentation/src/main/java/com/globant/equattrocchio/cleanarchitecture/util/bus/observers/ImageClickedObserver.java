package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class ImageClickedObserver extends BusObserver<ImageClickedObserver.ImageClicked> {
    public ImageClickedObserver(){ super(ImageClicked.class); }

    public static class ImageClicked{

        private int id;

        public ImageClicked(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }
    }
}
