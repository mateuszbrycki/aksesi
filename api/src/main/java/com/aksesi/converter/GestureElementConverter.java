package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;

import java.util.List;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
//TODO mbrycki test bonduary conditions
//TODO mbrycki refactor
public class GestureElementConverter extends AbstractConverter<GestureElement> {

    @Override
    public String convert(GestureElement element) throws ConversionException {
        List<Point> points = element.getPoints();

        if(points.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }

        Point firstPoint = points.get(0);
        Point secondPoint = points.get(points.size() - 1);

        //angle of inclination => a = tg(alpha)
        Double angleOfInclination = Math.toDegrees(Math.atan2(secondPoint.getY() - firstPoint.getY(), secondPoint.getX() - firstPoint.getX()));

        if(angleOfInclination < 0) {
            angleOfInclination += 180;
        }

        /*
            <0, 10) - HORIZONTAL
            <10, 80) - DIAGONAL_RIGHT
            <80, 110) - VERTICAL
            <110, 170) - DIAGONAL_LEFT
            <170, 180> - HORIZONTAL
         */
        if(angleOfInclination < 10 &&  angleOfInclination >= 0
                || angleOfInclination < 180 &&  angleOfInclination >= 170) {
            return LineDirection.HORIZONTAL.toString();
        } else if(angleOfInclination < 80  && angleOfInclination >= 10) {
            return LineDirection.DIAGONAL_RIGHT.toString();
        } else if(angleOfInclination < 110 && angleOfInclination >= 80) {
            return LineDirection.VERTICAL.toString();
        } else if(angleOfInclination < 170 && angleOfInclination >= 110) {
            return LineDirection.DIAGONAL_LEFT.toString();
        }

        throw new ConversionException("Unable to convert");
    }
}
