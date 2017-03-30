package com.aksesi.element.dto;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class PointDTO implements Serializable{

    private Long x;
    private Long y;

    public PointDTO() {}

    public PointDTO(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
