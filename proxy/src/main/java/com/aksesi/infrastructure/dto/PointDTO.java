package com.aksesi.infrastructure.dto;

import com.aksesi.application.element.Gesture;
import com.aksesi.infrastructure.annotation.Representation;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Representation(element = Gesture.Point.class)
public class PointDTO implements Serializable{

    private Float x;
    private Float y;

    public PointDTO() {}

    public PointDTO(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
