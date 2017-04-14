package com.aksesi.infrastructure.converter;

import com.aksesi.application.element.Gesture;
import com.aksesi.infrastructure.dto.GestureDTO;
import com.aksesi.infrastructure.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class GestureDTOConverter implements Converter<GestureDTO, Gesture> {

    private PointDTOConverter pointConverter;

    public GestureDTOConverter(@Autowired PointDTOConverter pointConverter) {
        this.pointConverter = pointConverter;
    }

    @Override
    public Gesture convert(GestureDTO gestureElementDTO) {
        List<Gesture.Point> points = convertPoints(gestureElementDTO.getPoints());
        return new Gesture(points);
    }

    private List<Gesture.Point> convertPoints(List<PointDTO> points) {

        return points.stream()
                .map(pointConverter::convert)
                .collect(Collectors.toList());
    }
}
