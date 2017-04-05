package com.aksesi.application.converter.strategy;

import com.aksesi.application.shape.Line;
import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.shape.direction.AngleOfInclination;
import com.aksesi.application.shape.direction.exception.ResolvingException;
import com.aksesi.application.shape.direction.IDirectionResolver;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Shape;
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

    public Shape convert(Gesture element) throws ConversionException {
        List<Gesture.Point> points = element.points();

        if(points.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }

        Gesture.Point firstPoint = points.get(0);
        Gesture.Point secondPoint = points.get(points.size() - 1);

        AngleOfInclination angleOfInclination = getAngleOfInclination(firstPoint, secondPoint);

        try {
            Line.LineDirection lineDirection = resolver.resolve(angleOfInclination);

            return new Line(lineDirection);
        } catch(ResolvingException e) {
            throw new ConversionException(e.getMessage());
        }
    }

    private AngleOfInclination getAngleOfInclination(Gesture.Point firstPoint, Gesture.Point secondPoint) {

        Double angleOfInclination = Math.toDegrees(Math.atan2(secondPoint.getY() - firstPoint.getY(), secondPoint.getX() - firstPoint.getX()));

        if(angleOfInclination < 0) {
            angleOfInclination += 180;
        }

        return new AngleOfInclination(angleOfInclination);
    }
}
