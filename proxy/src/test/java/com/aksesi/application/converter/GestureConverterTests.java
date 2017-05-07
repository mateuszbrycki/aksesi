package com.aksesi.application.converter;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.converter.strategy.IConversionStrategy;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Shape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GestureConverterTests {

    @Mock
    private IConversionStrategy conversionStrategy;

    @Mock
    private Gesture gestureElement;

    @Mock
    private Shape shape;

    private final String SHAPE_TEST = "SHAPE_TEST";

    @Before
    public void setup() {
        when(shape.toString()).thenReturn(SHAPE_TEST);
    }

    @Test
    public void convertedClassTest() {
        GestureConverter converter = new GestureConverter(conversionStrategy);

        assertEquals(Gesture.class, converter.converts());
    }

    @Test
    public void testGestureElementFlow() throws ConversionException {
        GestureConverter converter = new GestureConverter(conversionStrategy);
        when(conversionStrategy.convert(any(Gesture.class))).thenReturn(shape);

        String result = converter.convert(gestureElement);
        assertEquals(SHAPE_TEST, result);
    }

}
