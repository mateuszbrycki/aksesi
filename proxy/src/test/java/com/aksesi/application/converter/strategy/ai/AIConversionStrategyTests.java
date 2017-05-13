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
    private IGesturePointsModifier modifier;

    @Mock
    private IArraySupplier supplier;

    @Mock
    private Gesture gestureElement;

    @Test(expected = ConversionException.class)
    public void testEmptyGesture() throws ConversionException {

//        when(modifier.modify(any(List.class))).thenReturn(Collections.emptyList());

        AIConversionStrategy strategy = new AIConversionStrategy(modifier, supplier);

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
//        when(modifier.modify(any(List.class))).thenReturn(points);

        when(supplier.apply(any(Collection.class))).thenReturn(Collections.emptyList());

        AIConversionStrategy strategy = new AIConversionStrategy(modifier, supplier);

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.DIAGONAL_LEFT, ((Line)result).direction());

    }

    private void setupGesturePoints(List<Gesture.Point> points) {
        when(gestureElement.points()).thenReturn(points);
    }


}
