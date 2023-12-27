package com.designpattern.Factory;

public enum ImageEnum {
    RAT("rat"), SNAKE("snake"), SPIDER("spider");

    private String abbreviation;

    // Constructor
    private ImageEnum(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    // Method to get the abbreviation
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return abbreviation;
    }

    
}
