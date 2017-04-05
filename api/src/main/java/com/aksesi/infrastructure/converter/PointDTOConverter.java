package com.aksesi.infrastructure.converter;

import com.aksesi.application.element.Gesture;
import com.aksesi.infrastructure.dto.PointDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class PointDTOConverter implements Converter<PointDTO, Gesture.Point> {
    @Override
    public Gesture.Point convert(PointDTO pointDTO) {
        return new Gesture.Point(pointDTO.getX(), pointDTO.getY());
    }
}
