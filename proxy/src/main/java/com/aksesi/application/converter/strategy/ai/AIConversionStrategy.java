package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.converter.strategy.IConversionStrategy;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.Shape;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
@Component
public class AIConversionStrategy implements IConversionStrategy {

    private IGesturePointsModifier pointsModifier;

    public AIConversionStrategy(IGesturePointsModifier pointsModifier) {
        this.pointsModifier = pointsModifier;
    }

    @Override
    public Shape convert(Gesture element) throws ConversionException {

        List<Gesture.Point> pointList = element.points();

        if(pointList.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }
        
        //TODO mbrycki move gesture to the (0, 0) point
        //TODO mbrycki normalize points values
        //TODO mbrycki pass gesture to the neural network -> service layer
        //TODO mbrycki return the result


        
        
        return new Line(Line.LineDirection.DIAGONAL_LEFT);
    }
}
