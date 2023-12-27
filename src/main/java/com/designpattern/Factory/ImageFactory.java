package com.designpattern.Factory;

import java.util.Random;

import com.designpattern.App;

import javafx.scene.image.Image;

public class ImageFactory {

    public static Image CreateImage(String moleType) {
        Image image;
        Random r = new Random();

        if (moleType == "NORMAL") {
            image = new Image(App.class.getResource("images/rat.png").toExternalForm());
            return image;
        } else if (moleType == "NONE") {
            String moleString = r.nextBoolean() ? "snake" : "spider";
            image = new Image(App.class.getResource("images/" + moleString + ".png").toExternalForm());
            return image;
        }

        return null;
    }
}
