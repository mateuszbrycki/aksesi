package com.aksesi.element.converter;

import com.aksesi.element.Point;
import com.aksesi.element.dto.PointDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class PointDTOConverter implements Converter<PointDTO, Point> {
    @Override
    public Point convert(PointDTO pointDTO) {
        return new Point(pointDTO.getX(), pointDTO.getY());
    }
}
