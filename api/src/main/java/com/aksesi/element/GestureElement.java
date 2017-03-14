package com.aksesi.element;

import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class GestureElement implements PasswordElement{

    private List<Point> points;

    public GestureElement(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }
}
