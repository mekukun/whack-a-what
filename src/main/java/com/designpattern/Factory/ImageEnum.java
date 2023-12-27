package com.designpattern.Factory;

public enum ImageEnum {
    RAT("rat"),
    GOLDENRAT("goldenrat"), 
    SNAKE("snake"), 
    SPIDER("spider"),
    CARPET("carpet"),
    CLOCK("clock"),
    LAMP("lamp"),
    SOFA("sofa"),
    CABINET("cabinet");

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
