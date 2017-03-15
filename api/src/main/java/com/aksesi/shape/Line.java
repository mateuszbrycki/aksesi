package com.aksesi.shape;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public class Line extends Shape {

    private LineDirection lineDirection;

    public Line(LineDirection lineDirection) {
        this.lineDirection = lineDirection;
    }

    public LineDirection getLineDirection() {
        return lineDirection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + lineDirection.toString();
    }

}
