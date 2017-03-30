package com.aksesi.element.converter;

import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;
import com.aksesi.element.dto.GestureElementDTO;
import com.aksesi.element.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class GestureElementDTOConverter implements Converter<GestureElementDTO, GestureElement> {

    private PointDTOConverter pointConverter;

    public GestureElementDTOConverter(@Autowired PointDTOConverter pointConverter) {
        this.pointConverter = pointConverter;
    }

    @Override
    public GestureElement convert(GestureElementDTO gestureElementDTO) {
        List<Point> points = convertPoints(gestureElementDTO.getPoints());
        return new GestureElement(points);
    }

    private List<Point> convertPoints(List<PointDTO> points) {
        List<Point> result = new ArrayList<>();

        points.forEach(e -> result.add(pointConverter.convert(e)));

        return result;
    }
}
