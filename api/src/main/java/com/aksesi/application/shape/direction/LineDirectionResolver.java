package com.aksesi.application.shape.direction;

import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.direction.exception.ResolvingException;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@Component
public class LineDirectionResolver implements IDirectionResolver{

    public Line.LineDirection resolve(Double angleOfInclination) throws ResolvingException {

        //TODO mbrycki refactor
        if(angleOfInclination < 10 &&  angleOfInclination >= 0
                || angleOfInclination <= 180 && angleOfInclination >= 170) {
            return Line.LineDirection.HORIZONTAL;
        } else if(angleOfInclination < 80  && angleOfInclination >= 10) {
            return Line.LineDirection.DIAGONAL_RIGHT;
        } else if(angleOfInclination < 110 && angleOfInclination >= 80) {
            return Line.LineDirection.VERTICAL;
        } else if(angleOfInclination < 170 && angleOfInclination >= 110) {
            return Line.LineDirection.DIAGONAL_LEFT;
        }

        throw new ResolvingException("Unable to resolve");
    }
}
