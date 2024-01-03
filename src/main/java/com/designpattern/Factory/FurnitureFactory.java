package com.designpattern.Factory;

import com.designpattern.App;
import com.designpattern.Entity.Furniture;
import com.designpattern.Entity.Hole;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FurnitureFactory {

    public Furniture createFurniture(String type){
        Image image;
        ImageView imageView;
        Hole newHole;

        switch (type) {
            case "CARPET":
                image = ImageGenerator.GenerateImage(ImageEnum.CARPET.getAbbreviation());
                imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(200);
                imageView.setFitWidth(1050);
                imageView.setPreserveRatio(false);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setTranslateX(0);
                imageView.setTranslateY(300);

                newHole = new Hole(-120, 250);

                return new Furniture(newHole, imageView);
                
            case "CLOCK":
                image = ImageGenerator.GenerateImage(ImageEnum.CLOCK.getAbbreviation());
                imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setTranslateX(0);
                imageView.setTranslateY(-130);
                

                newHole = new Hole(0, -130);

                return new Furniture(newHole, imageView);
            
            case "LAMP":
                image = ImageGenerator.GenerateImage(ImageEnum.LAMP.getAbbreviation());
                imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setTranslateX(-300);
                imageView.setTranslateY(-25);

                newHole = new Hole(-300, -40);

                return new Furniture(newHole, imageView);
            
            case "SOFA":
                image = ImageGenerator.GenerateImage(ImageEnum.SOFA.getAbbreviation());
                imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(600);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setTranslateX(200);
                imageView.setTranslateY(150);

                newHole = new Hole(200, 10);

                return new Furniture(newHole, imageView);

            case "CABINET":
                image = ImageGenerator.GenerateImage(ImageEnum.CABINET.getAbbreviation());
                imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitWidth(300);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setCache(true);
                imageView.setTranslateX(-300);
                imageView.setTranslateY(160);

                newHole = new Hole(-430, 120);

                return new Furniture(newHole, imageView);
        }
        return null;
    }
}
