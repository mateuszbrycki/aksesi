package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.Shape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AIConversionStrategyTests {

    @Mock
    private IGestureNormalizer normalizer;

    @Mock
    private INeuralNetwork neuralNetwork;

    @Mock
    private Gesture gestureElement;

    @Test(expected = ConversionException.class)
    public void testEmptyGesture() throws ConversionException {

//        when(modifier.align(any(List.class))).thenReturn(Collections.emptyList());

        AIConversionStrategy strategy = new AIConversionStrategy(normalizer, neuralNetwork);

        strategy.convert(gestureElement);
    }

    @Test
    public void testGestureWithMoreThanTwoPoints() throws ConversionException {

        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(2f, 1f),
                new Gesture.Point(3f, 1f)
        );

        setupGesturePoints(points);
//        when(modifier.align(any(List.class))).thenReturn(points);

        when(normalizer.normalize(any(List.class))).thenReturn(new double[0]);

        AIConversionStrategy strategy = new AIConversionStrategy(normalizer, neuralNetwork);

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.DIAGONAL_LEFT, ((Line)result).direction());

    }

    private void setupGesturePoints(List<Gesture.Point> points) {
        when(gestureElement.points()).thenReturn(points);
    }


}
