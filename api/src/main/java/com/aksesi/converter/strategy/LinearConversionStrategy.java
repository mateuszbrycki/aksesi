package com.aksesi.converter.strategy;

import com.aksesi.shape.Line;
import com.aksesi.shape.LineDirection;
import com.aksesi.converter.exception.ConversionException;
import com.aksesi.converter.exception.ResolvingException;
import com.aksesi.converter.resolver.IDirectionResolver;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;
import com.aksesi.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@Component
public class LinearConversionStrategy implements IConversionStrategy {

    private IDirectionResolver resolver;

    public LinearConversionStrategy(@Autowired IDirectionResolver resolver) {
        this.resolver = resolver;
    }

    public Shape convert(GestureElement element) throws ConversionException {
        List<Point> points = element.getPoints();

        if(points.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }

        Point firstPoint = points.get(0);
        Point secondPoint = points.get(points.size() - 1);

        Double angleOfInclination = getAngleOfInclination(firstPoint, secondPoint);

        try {
            LineDirection lineDirection = resolver.resolve(angleOfInclination);

            return new Line(lineDirection);
        } catch(ResolvingException e) {
            throw new ConversionException(e.getMessage());
        }
    }

    private Double getAngleOfInclination(Point firstPoint, Point secondPoint) {

        Double angleOfInclination = Math.toDegrees(Math.atan2(secondPoint.getY() - firstPoint.getY(), secondPoint.getX() - firstPoint.getX()));

        if(angleOfInclination < 0) {
            angleOfInclination += 180;
        }

        return angleOfInclination;
    }
}
