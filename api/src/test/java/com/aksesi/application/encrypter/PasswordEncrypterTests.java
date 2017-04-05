package com.aksesi.application.encrypter;

import com.aksesi.application.converter.AbstractConverter;
import com.aksesi.application.converter.GestureConverter;
import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.element.Character;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.element.PasswordElement;
import com.aksesi.application.element.Password;
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
public class PasswordEncrypterTests {

    @Mock
    private AbstractConverter abstractConverter;

    @Mock
    private AbstractConverter abstractConverter1;

    @Mock
    private Password password;

    private PasswordEncrypter conversionService;
    private Character character = new Character('a');
    private Gesture gestureElement1 = new Gesture(Arrays.asList());
    private Gesture gestureElement2 = new Gesture(Arrays.asList());

    @Before
    public void setupConversionService() {
        //required new instance for each test -> converterMap instance
        conversionService = new PasswordEncrypter();
    }

    @Test
    public void registerOneConverterTest() {

        when(abstractConverter.converts()).thenReturn(AbstractConverter.class);
        registerConverters(abstractConverter);

        Collection<AbstractConverter> converters = conversionService.converters();
        assertEquals(1, converters.size());
    }

    @Test
    public void registerTwoConvertersTest() {

        setupConverter();
        registerConverters(abstractConverter, abstractConverter);

        Collection<AbstractConverter> converters = conversionService.converters();
        assertEquals(2, converters.size());
    }

    @Test
    public void removeAllConvertersTest() {

        setupConverter();
        registerConverters(abstractConverter, abstractConverter);

        Collection<AbstractConverter> converters = conversionService.converters();
        assertEquals(2, converters.size());

        conversionService.removeAllConverters();

        converters = conversionService.converters();
        assertEquals(0, converters.size());
    }

    @Test
    public void convertersCallsTest() throws ConversionException {

        when(abstractConverter.converts()).thenReturn(Character.class);
        when(abstractConverter1.converts()).thenReturn(Gesture.class);

        registerConverters(abstractConverter, abstractConverter1);

        setupPasswordElements(gestureElement1, character, gestureElement2);

        conversionService.encrypt(password);

        //verify that converters were called
        verify(abstractConverter, times(1)).convert(character);
        verify(abstractConverter1, times(1)).convert(gestureElement1);
        verify(abstractConverter1, times(1)).convert(gestureElement2);
    }

    @Test
    public void conversionServiceProcessingPasswordTest() throws ConversionException {

        //setup converters
        when(abstractConverter.converts()).thenReturn(Character.class);
        when(abstractConverter1.converts()).thenReturn(Gesture.class);

        registerConverters(abstractConverter, abstractConverter1);

        setupPasswordElements(gestureElement1, character, gestureElement2);

        when(abstractConverter.convert(character)).thenReturn("character_1");
        when(abstractConverter1.convert(gestureElement1)).thenReturn("gesture_1");
        when(abstractConverter1.convert(gestureElement2)).thenReturn("gesture_2");

        String result = conversionService.encrypt(password);

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
                .thenReturn(GestureConverter.class);

    }
}
