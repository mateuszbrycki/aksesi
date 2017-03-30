package com.aksesi;

import com.aksesi.converter.AbstractConverter;
import com.aksesi.converter.CharacterElementConverter;
import com.aksesi.converter.GestureElementConverter;
import com.aksesi.converter.resolver.IDirectionResolver;
import com.aksesi.converter.resolver.LineDirectionResolver;
import com.aksesi.converter.strategy.IConversionStrategy;
import com.aksesi.converter.strategy.LinearConversionStrategy;
import com.aksesi.element.CharacterElement;
import com.aksesi.element.GestureElement;
import com.aksesi.element.PasswordElement;
import com.aksesi.element.Point;
import com.aksesi.element.Password;
import com.aksesi.service.PasswordConversionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 16/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AksesiIntegrationTests {

    PasswordConversionService passwordConversionService;
    AbstractConverter gestureElementConverter;
    AbstractConverter characterElementConverter;

    IConversionStrategy linearConversionStrategy;
    IDirectionResolver lineDirectionResolver;

    Password password;

    @Before
    public void setup() {
        lineDirectionResolver = new LineDirectionResolver();
        linearConversionStrategy = new LinearConversionStrategy(lineDirectionResolver);

        gestureElementConverter = new GestureElementConverter(linearConversionStrategy);
        characterElementConverter = new CharacterElementConverter();

        passwordConversionService = new PasswordConversionService();
        passwordConversionService
                .registerConverter(gestureElementConverter)
                .registerConverter(characterElementConverter);
    }


    @Test
    public void emptyPasswordTest() {
        setupPassword(

        );

        String result = passwordConversionService.process(password);

        assertTrue(result.isEmpty());
    }

    @Test
    public void onlyCharacterElementTest() {
        setupPassword(
                new CharacterElement('a')
        );

        String result = passwordConversionService.process(password);

        assertEquals("a", result);

    }

    @Test
    public void onlyGestureElementTest() {
        setupPassword(
                new GestureElement(Arrays.asList(
                        new Point(1L, 1L),
                        new Point(2L, 2L)
                ))
        );

        String result = passwordConversionService.process(password);

        assertEquals("LineDIAGONAL_RIGHT", result);
    }

    @Test
    public void combineGesturesAndCharactersElementTest1() {
        setupPassword(
                new GestureElement(Arrays.asList( //LineDIAGONAL_RIGHT
                        new Point(1L, 1L),
                        new Point(2L, 2L)
                )),
                new CharacterElement('a'), //a
                new CharacterElement('b'), //b
                new CharacterElement('d'), //d
                new GestureElement(Arrays.asList( //LineDIAGONAL_LEFT
                        new Point(1L, 2L),
                        new Point(2L, 1L)
                ))

        );

        String result = passwordConversionService.process(password);

        assertEquals("LineDIAGONAL_RIGHTabdLineDIAGONAL_LEFT", result);
    }

    @Test
    public void combineGesturesAndCharactersElementTest2() {
        setupPassword(
                new GestureElement(Arrays.asList( //LineDIAGONAL_RIGHT
                        new Point(1L, 1L),
                        new Point(2L, 2L)
                )),
                new CharacterElement('a'), //a
                new GestureElement(Arrays.asList( //LineDIAGONAL_VETRICAL
                        new Point(1L, 1L),
                        new Point(1L, 2L)
                )),
                new CharacterElement('b'), //b
                new GestureElement(Arrays.asList( //LineHORIZONTAL
                        new Point(1L, 1L),
                        new Point(2L, 1L)
                )),
                new CharacterElement('\\'), // \
                new CharacterElement('$') //d


        );

        String result = passwordConversionService.process(password);

        assertEquals("LineDIAGONAL_RIGHTaLineVERTICALbLineHORIZONTAL\\$".length(), result.length());
        assertEquals("LineDIAGONAL_RIGHTaLineVERTICALbLineHORIZONTAL\\$", result);
    }

    @Test
    public void specialCharactersTest2() {
        setupPassword(
                new CharacterElement('/'), // /
                new CharacterElement('$'), // $
                new CharacterElement('\\'), // \
                new CharacterElement('~'), // ~
                new CharacterElement('`') // `


        );

        String result = passwordConversionService.process(password);

        assertEquals("/$\\~`".length(), result.length());
        assertEquals("/$\\~`", result);
    }


    private void setupPassword(PasswordElement...elements) {
        List<PasswordElement> passwordElements = Arrays.asList(elements);
        password = new Password(passwordElements);
    }
}
