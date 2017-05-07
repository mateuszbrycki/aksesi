package com.aksesi.application.shape.direction;

import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.direction.exception.ResolvingException;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@Component
public class LineDirectionResolver implements IDirectionResolver{

    public Line.LineDirection resolve(AngleOfInclination angleOfInclination) throws ResolvingException {

        Double angleValue = angleOfInclination.angle();
        if(isHorizontal(angleValue)) {
            return Line.LineDirection.HORIZONTAL;
        } else if(isDiagonalRight(angleValue)) {
            return Line.LineDirection.DIAGONAL_RIGHT;
        } else if(isVertical(angleValue)) {
            return Line.LineDirection.VERTICAL;
        } else if(isDiagonalLeft(angleValue)) {
            return Line.LineDirection.DIAGONAL_LEFT;
        }

        throw new ResolvingException("Unable to resolve");
    }

    private Boolean isHorizontal(Double angleOfInclination) {
        return (angleOfInclination >= 0 && angleOfInclination < 10)
                || (angleOfInclination >= 170 && angleOfInclination <= 180);
    }

    private Boolean isDiagonalRight(Double angleOfInclination) {
        return angleOfInclination >= 10 && angleOfInclination < 80;
    }

    private Boolean isVertical(Double angleOfInclination) {
        return angleOfInclination >= 80 && angleOfInclination < 110;
    }

    private Boolean isDiagonalLeft(Double angleOfInclination) {
        return angleOfInclination >= 110 && angleOfInclination < 170;
    }
}
