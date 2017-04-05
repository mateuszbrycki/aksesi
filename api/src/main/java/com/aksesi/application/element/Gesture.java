package com.aksesi.application.element;

import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class Gesture implements PasswordElement{

    private List<Point> points;

    public Gesture(List<Point> points) {
        this.points = points;
    }

    public List<Point> points() {
        return points;
    }

    public static class Point {

        private Long x;
        private Long y;

        public Point(Long x, Long y) {
            this.x = x;
            this.y = y;
        }

        public Long getX() {
            return x;
        }

        public Long getY() {
            return y;
        }
    }
}
