package com.aksesi.converter.strategy;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.converter.exception.ResolvingException;
import com.aksesi.converter.resolver.IDirectionResolver;
import com.aksesi.converter.resolver.LineDirectionResolver;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;
import com.aksesi.shape.Line;
import com.aksesi.shape.LineDirection;
import com.aksesi.shape.Shape;
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
    private GestureElement gestureElement;

    @Test(expected = ConversionException.class)
    public void tooShortGestureTest() throws ConversionException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);

        setupGesturePoints(Collections.<Point>emptyList());

        Shape result = strategy.convert(gestureElement);
    }

    @Test(expected = ConversionException.class)
    public void resolverThrowsExceptionTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenThrow(new ResolvingException("Message"));
        setupGesturePoints(
                Arrays.asList(
                        new Point(1L, 1L),
                        new Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);
    }

    @Test
    public void horizontalLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(LineDirection.HORIZONTAL);

        setupGesturePoints(
                Arrays.asList(
                        new Point(1L, 1L),
                        new Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(LineDirection.HORIZONTAL, ((Line)result).getLineDirection());
    }

    @Test
    public void verticalLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(LineDirection.VERTICAL);

        setupGesturePoints(
                Arrays.asList(
                    new Point(1L, 1L),
                    new Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(LineDirection.VERTICAL, ((Line)result).getLineDirection());
    }

    @Test
    public void diagonalRightLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(LineDirection.DIAGONAL_RIGHT);

        setupGesturePoints(
                Arrays.asList(
                    new Point(1L, 1L),
                    new Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(LineDirection.DIAGONAL_RIGHT, ((Line)result).getLineDirection());
    }

    @Test
    public void diagonalLeftLineGestureTest() throws ConversionException, ResolvingException {

        LinearConversionStrategy strategy = new LinearConversionStrategy(resolver);
        when(resolver.resolve(anyDouble())).thenReturn(LineDirection.DIAGONAL_LEFT);

        setupGesturePoints(
                Arrays.asList(
                    new Point(1L, 1L),
                    new Point(2L, 1L)
                )
        );

        Shape result = strategy.convert(gestureElement);

        assertTrue(result instanceof Line);
        assertEquals(LineDirection.DIAGONAL_LEFT, ((Line)result).getLineDirection());
    }



    private void setupGesturePoints(List<Point> points) {
        when(gestureElement.getPoints()).thenReturn(points);
    }

}
