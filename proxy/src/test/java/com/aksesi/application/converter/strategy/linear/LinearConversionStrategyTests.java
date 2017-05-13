package com.aksesi.application.converter.strategy.linear;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.converter.strategy.linear.LinearConversionStrategy;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.Shape;
import com.aksesi.application.shape.direction.AngleOfInclination;
import com.aksesi.application.shape.direction.IDirectionResolver;
import com.aksesi.application.shape.direction.exception.ResolvingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LinearConversionStrategyTests {

    @Mock
    private IDirectionResolver resolver;

    @Mock
    private Gesture gestureElement;

    @Test(expected = ConversionException.class)
    public void tooShortGestureTest() throws ConversionException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);

        setupGesturePoints(Collections.<Gesture.Point>emptyList());

        Shape result = strategy.convert(gestureElement);
    }

    @Test(expected = ConversionException.class)
    public void resolverThrowsExceptionTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(any(AngleOfInclination.class))).thenThrow(new ResolvingException("Message"));
        setupGesturePoints(
                Arrays.asList(
                        new Gesture.Point(1f, 1f),
                        new Gesture.Point(2f, 1f)
                )
        );

        Shape result = strategy.convert(gestureElement);
    }

    @Test
    public void horizontalLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(any(AngleOfInclination.class))).thenReturn(Line.LineDirection.HORIZONTAL);

        setupGesturePoints(
                Arrays.asList(
                        new Gesture.Point(1f, 1f),
                        new Gesture.Point(2f, 1f)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.HORIZONTAL, ((Line)result).direction());
    }

    @Test
    public void verticalLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(any(AngleOfInclination.class))).thenReturn(Line.LineDirection.VERTICAL);

        setupGesturePoints(
                Arrays.asList(
                    new Gesture.Point(1f, 1f),
                    new Gesture.Point(2f, 1f)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.VERTICAL, ((Line)result).direction());
    }

    @Test
    public void diagonalRightLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(any(AngleOfInclination.class))).thenReturn(Line.LineDirection.DIAGONAL_RIGHT);

        setupGesturePoints(
                Arrays.asList(
                    new Gesture.Point(1f, 1f),
                    new Gesture.Point(2f, 1f)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.DIAGONAL_RIGHT, ((Line)result).direction());
    }

    @Test
    public void diagonalLeftLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(any(AngleOfInclination.class))).thenReturn(Line.LineDirection.DIAGONAL_LEFT);

        setupGesturePoints(
                Arrays.asList(
                    new Gesture.Point(1f, 1f),
                    new Gesture.Point(2f, 1f)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.DIAGONAL_LEFT, ((Line)result).direction());
    }



    private void setupGesturePoints(List<Gesture.Point> points) {
        when(gestureElement.points()).thenReturn(points);
    }

}
