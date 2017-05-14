package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.converter.strategy.IConversionStrategy;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.Shape;
import org.encog.util.arrayutil.NormalizeArray;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
@Component
public class AIConversionStrategy implements IConversionStrategy {

    private IGesturePointsAligner pointsAligner;
    private IFormattedInputSupplier formattedInputSupplier;
    private IGestureResizer gestureResizer;

    public AIConversionStrategy(IGesturePointsAligner pointsAligner,
                                IFormattedInputSupplier formattedInputSupplier,
                                IGestureResizer gestureResizer) {
        this.pointsAligner = pointsAligner;
        this.formattedInputSupplier = formattedInputSupplier;
        this.gestureResizer = gestureResizer;
    }

    @Override
    public Shape convert(Gesture element) throws ConversionException {

        List<Gesture.Point> pointsList = element.points();

        if(pointsList.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }

        pointsList = pointsAligner.align(pointsList);
        pointsList = gestureResizer.resize(pointsList);
        double[] normalizedArray = normalizePoints(pointsList);

        //TODO mbrycki pass gesture to the neural network -> service layer
        //TODO mbrycki return the result

        return new Line(Line.LineDirection.DIAGONAL_LEFT);
    }

    private double[] normalizePoints(List<Gesture.Point> points) {
        List<Double> listOfElements = formattedInputSupplier.apply(points);

        double[] arrayOfElements = new double[listOfElements.size()];

        IntStream.range(0, listOfElements.size())
                .forEach(idx -> arrayOfElements[idx] = listOfElements.get(idx));

        NormalizeArray normalizeArray = new NormalizeArray();
        normalizeArray.setNormalizedHigh(1);
        normalizeArray.setNormalizedLow(-1);
        double[] normalizedArray = normalizeArray.process(arrayOfElements);

        return normalizedArray;
    }
}
