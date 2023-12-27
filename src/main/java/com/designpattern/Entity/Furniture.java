package com.designpattern.Entity;

import javafx.scene.image.ImageView;

public class Furniture {
    Hole hole;
    ImageView image;
    
    public Furniture(Hole hole, ImageView image) {
        this.hole = hole;
        this.image = image;
    }

    public Hole getHole() {
        return hole;
    }

    public void setHole(Hole hole) {
        this.hole = hole;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

}
