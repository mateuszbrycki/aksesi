package com.aksesi.element.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class GestureElementDTO implements PasswordElementDTO, Serializable {

    private List<PointDTO> points;

    private String type = "gesture";

    public GestureElementDTO() {}

    public GestureElementDTO(List<PointDTO> points) {
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
