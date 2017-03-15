package com.aksesi.converter.resolver;

import com.aksesi.converter.LineDirection;
import com.aksesi.converter.exception.ResolvingException;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public class LineDirectionResolver implements IDirectionResolver{

    public LineDirection resolve(Double angleOfInclination) throws ResolvingException {

        if(angleOfInclination < 10 &&  angleOfInclination >= 0
                || angleOfInclination <= 180 && angleOfInclination >= 170) {
            return LineDirection.HORIZONTAL;
        } else if(angleOfInclination < 80  && angleOfInclination >= 10) {
            return LineDirection.DIAGONAL_RIGHT;
        } else if(angleOfInclination < 110 && angleOfInclination >= 80) {
            return LineDirection.VERTICAL;
        } else if(angleOfInclination < 170 && angleOfInclination >= 110) {
            return LineDirection.DIAGONAL_LEFT;
        }

        throw new ResolvingException("Unable to resolve");
    }
}
