package com.aksesi.shape;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public enum LineDirection {

    VERTICAL ("VERTICAL"),
    HORIZONTAL ("HORIZONTAL"),
    DIAGONAL_RIGHT("DIAGONAL_RIGHT"),
    DIAGONAL_LEFT("DIAGONAL_LEFT");

    private String name;

    LineDirection(String name) {
        this.name = name;
    }

    public String toString() { return this.name(); }

}
