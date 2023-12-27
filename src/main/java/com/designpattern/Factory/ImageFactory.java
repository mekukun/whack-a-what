package com.designpattern.Factory;

import com.designpattern.App;

import javafx.scene.image.Image;

public class ImageFactory {
    

    public static Image CreateImage(String imageString){
        Image image = new Image(App.class.getResource("images/"+imageString+".png").toExternalForm());
        return image;
    }
}


