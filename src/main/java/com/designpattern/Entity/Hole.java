package com.designpattern.Entity;

import java.util.Objects;

public class Hole {
    private double x_coordinate, y_coordinate;
    private Pest pest;
    public Hole(double x_coordinate, double y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    public void setPest(Pest pest){
        if(!Objects.isNull(pest)){
            pest.setCoordinateTranslation(x_coordinate, y_coordinate);
        }else{
            System.out.println("Hole is occupied");
        }
    }
}
