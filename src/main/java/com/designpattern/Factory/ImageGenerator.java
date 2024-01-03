package com.designpattern.Factory;

import com.designpattern.App;

import javafx.scene.image.Image;

public class ImageGenerator {
    
    public static Image GenerateImage(String imageString){
        return new Image(App.class.getResource("images/"+imageString+".png").toExternalForm());
    }
}
