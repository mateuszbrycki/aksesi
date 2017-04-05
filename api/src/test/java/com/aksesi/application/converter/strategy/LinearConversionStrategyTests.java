package com.aksesi.application.converter.strategy;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.shape.direction.exception.ResolvingException;
import com.aksesi.application.shape.direction.IDirectionResolver;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.Shape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
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
        when(resolver.resolve(anyDouble())).thenThrow(new ResolvingException("Message"));
        setupGesturePoints(
                Arrays.asList(
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);
    }

    @Test
    public void horizontalLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(Line.LineDirection.HORIZONTAL);

        setupGesturePoints(
                Arrays.asList(
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.HORIZONTAL, ((Line)result).direction());
    }

    @Test
    public void verticalLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(Line.LineDirection.VERTICAL);

        setupGesturePoints(
                Arrays.asList(
                    new Gesture.Point(1L, 1L),
                    new Gesture.Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.VERTICAL, ((Line)result).direction());
    }

    @Test
    public void diagonalRightLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(Line.LineDirection.DIAGONAL_RIGHT);

        setupGesturePoints(
                Arrays.asList(
                    new Gesture.Point(1L, 1L),
                    new Gesture.Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(Line.LineDirection.DIAGONAL_RIGHT, ((Line)result).direction());
    }

    @Test
    public void diagonalLeftLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(Line.LineDirection.DIAGONAL_LEFT);

        setupGesturePoints(
                Arrays.asList(
                    new Gesture.Point(1L, 1L),
                    new Gesture.Point(2L, 1L)
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
