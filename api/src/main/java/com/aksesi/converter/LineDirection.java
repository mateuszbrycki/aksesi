package com.aksesi.converter;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public enum LineDirection {

    VERTICAL ("VERTICAL"),
    HORIZONTAL ("HORIZONTAL"),
    DIAGONAL_RIGHT("DIAGONAL_RIGHT"),
    DIAGONAL_LEFT("DIAGONAL_LEFT");

    private final String name;

    LineDirection(String name) {
        this.name = name;
    }

    public String toString() { return this.name(); }

}
