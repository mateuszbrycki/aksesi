package com.aksesi.infrastructure.dto;

import com.aksesi.application.element.Gesture;
import com.aksesi.infrastructure.annotation.Representation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Representation(element = Gesture.class)
public class GestureDTO implements PasswordElementDTO, Serializable {

    private List<PointDTO> points;

    private String type = "gesture";

    public GestureDTO() {}

    public GestureDTO(List<PointDTO> points) {
        this.points = points;
    }

    public void setPoints(List<PointDTO> points) {
        this.points = points;
    }

    public List<PointDTO> getPoints() {
        return points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
