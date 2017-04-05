package com.aksesi.application.shape;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public class Line extends Shape {

    private LineDirection lineDirection;

    public Line(LineDirection lineDirection) {
        this.lineDirection = lineDirection;
    }

    public LineDirection direction() {
        return lineDirection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + lineDirection.toString();
    }


    public static enum LineDirection {

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
}
