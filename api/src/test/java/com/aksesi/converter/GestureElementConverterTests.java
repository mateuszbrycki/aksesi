package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.converter.exception.ResolvingException;
import com.aksesi.converter.resolver.LineDirectionResolver;
import com.aksesi.converter.strategy.IConversionStrategy;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Point;
import com.aksesi.shape.LineDirection;
import com.aksesi.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GestureElementConverterTests {

    @Mock
    private IConversionStrategy conversionStrategy;

    @Mock
    private GestureElement gestureElement;

    @Mock
    private Shape shape;

    private final String SHAPE_TEST = "SHAPE_TEST";

    @Before
    public void setup() {
        when(shape.toString()).thenReturn(SHAPE_TEST);
    }

    @Test
    public void convertedClassTest() {
        GestureElementConverter converter = new GestureElementConverter(conversionStrategy);

        assertEquals(GestureElement.class, converter.converts());
    }

    @Test
    public void testGestureElementFlow() throws ConversionException {
        GestureElementConverter converter = new GestureElementConverter(conversionStrategy);
        when(conversionStrategy.convert(any(GestureElement.class))).thenReturn(shape);

        String result = converter.convert(gestureElement);
        assertEquals(SHAPE_TEST, result);
    }

}
