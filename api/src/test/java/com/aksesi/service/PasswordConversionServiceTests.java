package com.aksesi.service;

import com.aksesi.converter.AbstractConverter;
import com.aksesi.converter.GestureElementConverter;
import com.aksesi.converter.exception.ConversionException;
import com.aksesi.element.CharacterElement;
import com.aksesi.element.GestureElement;
import com.aksesi.element.PasswordElement;
import com.aksesi.element.Password;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class PasswordConversionServiceTests {

    @Mock
    private AbstractConverter abstractConverter;

    @Mock
    private AbstractConverter abstractConverter1;

    @Mock
    private Password password;

    private PasswordConversionService conversionService;
    private CharacterElement characterElement = new CharacterElement('a');
    private GestureElement gestureElement1 = new GestureElement(Arrays.asList());
    private GestureElement gestureElement2 = new GestureElement(Arrays.asList());

    @Before
    public void setupConversionService() {
        //required new instance for each test -> converterMap instance
        conversionService = new PasswordConversionService();
    }

    @Test
    public void registerOneConverterTest() {

        when(abstractConverter.converts()).thenReturn(AbstractConverter.class);
        registerConverters(abstractConverter);

        Collection<AbstractConverter> converters = conversionService.getConverters();
        assertEquals(1, converters.size());
    }

    @Test
    public void registerTwoConvertersTest() {

        setupConverter();
        registerConverters(abstractConverter, abstractConverter);

        Collection<AbstractConverter> converters = conversionService.getConverters();
        assertEquals(2, converters.size());
    }

    @Test
    public void removeAllConvertersTest() {

        setupConverter();
        registerConverters(abstractConverter, abstractConverter);

        Collection<AbstractConverter> converters = conversionService.getConverters();
        assertEquals(2, converters.size());

        conversionService.removeAllConverters();

        converters = conversionService.getConverters();
        assertEquals(0, converters.size());
    }

    @Test
    public void convertersCallsTest() throws ConversionException {

        when(abstractConverter.converts()).thenReturn(CharacterElement.class);
        when(abstractConverter1.converts()).thenReturn(GestureElement.class);

        registerConverters(abstractConverter, abstractConverter1);

        setupPasswordElements(gestureElement1, characterElement, gestureElement2);

        conversionService.process(password);

        //verify that converters were called
        verify(abstractConverter, times(1)).convert(characterElement);
        verify(abstractConverter1, times(1)).convert(gestureElement1);
        verify(abstractConverter1, times(1)).convert(gestureElement2);
    }

    @Test
    public void conversionServiceProcessingPasswordTest() throws ConversionException {

        //setup converters
        when(abstractConverter.converts()).thenReturn(CharacterElement.class);
        when(abstractConverter1.converts()).thenReturn(GestureElement.class);

        registerConverters(abstractConverter, abstractConverter1);

        setupPasswordElements(gestureElement1, characterElement, gestureElement2);

        when(abstractConverter.convert(characterElement)).thenReturn("character_1");
        when(abstractConverter1.convert(gestureElement1)).thenReturn("gesture_1");
        when(abstractConverter1.convert(gestureElement2)).thenReturn("gesture_2");

        String result = conversionService.process(password);

        //verify processing method output
        assertEquals("gesture_1character_1gesture_2", result);
    }

    private void setupPasswordElements(PasswordElement...elements) {
        when(password.getElements()).thenReturn(Arrays.asList(elements));
    }

    private void registerConverters(AbstractConverter...converters) {
        for(AbstractConverter converter : converters) {
            conversionService.registerConverter(converter);
        }
    }

    private void setupConverter() {
        when(abstractConverter.converts())
                .thenReturn(AbstractConverter.class)
                .thenReturn(GestureElementConverter.class);

    }
}
