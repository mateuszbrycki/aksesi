package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.element.CharacterElement;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GestureElementConverterTests {

    @Mock
    GestureElement gestureElement;

    @Test
    public void convertedClassTest() {
        GestureElementConverter converter = new GestureElementConverter();

        assertEquals(GestureElement.class, converter.converts());
    }

    @Test(expected = ConversionException.class)
    public void tooShortGestureTest() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter();

        List<Point> points = Collections.emptyList();
        when(gestureElement.getPoints()).thenReturn(points);

        String result = converter.convert(gestureElement);
    }

    @Test
    public void horizontalLineGestureTest() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter();

        List<Point> points = Arrays.asList(
                new Point(1L, 1L),
                new Point(2L, 1L)
        );
        when(gestureElement.getPoints()).thenReturn(points);

        String result = converter.convert(gestureElement);
        assertEquals("HORIZONTAL", result);
    }

    @Test
    public void verticalLineGestureTest() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter();

        List<Point> points = Arrays.asList(
                new Point(1L, 1L),
                new Point(1L, 2L)
        );
        when(gestureElement.getPoints()).thenReturn(points);

        String result = converter.convert(gestureElement);
        assertEquals("VERTICAL", result);
    }

    @Test
    public void diagonalRightLineGestureTest() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter();

        List<Point> points = Arrays.asList(
                new Point(1L, 1L),
                new Point(2L, 2L)
        );
        when(gestureElement.getPoints()).thenReturn(points);

        String result = converter.convert(gestureElement);
        assertEquals("DIAGONAL_RIGHT", result);
    }

    @Test
    public void diagonalLeftLineGestureTest() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter();

        List<Point> points = Arrays.asList(
                new Point(1L, 2L),
                new Point(2L, 1L)
        );
        when(gestureElement.getPoints()).thenReturn(points);

        String result = converter.convert(gestureElement);
        assertEquals("DIAGONAL_LEFT", result);
    }

    @Test
    public void diagonalLeftLineGestureTest2() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter();

        List<Point> points = Arrays.asList(
                new Point(-2L, 2L),
                new Point(-1L, 1L)
        );
        when(gestureElement.getPoints()).thenReturn(points);

        String result = converter.convert(gestureElement);
        assertEquals("DIAGONAL_LEFT", result);
    }
}
