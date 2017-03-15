package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.converter.exception.ResolvingException;
import com.aksesi.converter.resolver.IDirectionResolver;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;

import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class GestureElementConverter extends AbstractConverter<GestureElement> {

    private IDirectionResolver directionResolver;

    public GestureElementConverter(IDirectionResolver resolver) {
        this.directionResolver = resolver;
    }

    @Override
    public String convert(GestureElement element) throws ConversionException {
        List<Point> points = element.getPoints();

        if(points.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }

        Point firstPoint = points.get(0);
        Point secondPoint = points.get(points.size() - 1);

        Double angleOfInclination = genAngleOfInclination(firstPoint, secondPoint);

        try {
            LineDirection lineDirection = directionResolver.resolve(angleOfInclination);

            return lineDirection.toString();
        } catch(ResolvingException e) {
            throw new ConversionException(e.getMessage());
        }
    }

    private Double genAngleOfInclination(Point firstPoint, Point secondPoint) {

        Double angleOfInclination = Math.toDegrees(Math.atan2(secondPoint.getY() - firstPoint.getY(), secondPoint.getX() - firstPoint.getX()));

        if(angleOfInclination < 0) {
            angleOfInclination += 180;
        }

        return angleOfInclination;
    }
}
