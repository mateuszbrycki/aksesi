package com.aksesi.infrastructure.dto;

import com.aksesi.application.element.Gesture;
import com.aksesi.infrastructure.annotation.Representation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Representation(element = Gesture.class)
public class GestureDTO extends PasswordElementDTO implements Serializable {

    private List<PointDTO> points;

    private static final String ELEMENT_TYPE = "character";

    public GestureDTO() {
        super(ELEMENT_TYPE);
    }

    public GestureDTO(List<PointDTO> points) {
        super(ELEMENT_TYPE);
        this.points = points;
    }

    public void setPoints(List<PointDTO> points) {
        this.points = points;
    }

    public List<PointDTO> getPoints() {
        return points;
    }
}
