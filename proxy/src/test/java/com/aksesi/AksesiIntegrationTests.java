package com.aksesi;

import com.aksesi.application.converter.AbstractConverter;
import com.aksesi.application.converter.CharacterConverter;
import com.aksesi.application.converter.GestureConverter;
import com.aksesi.application.converter.strategy.IConversionStrategy;
import com.aksesi.application.converter.strategy.LinearConversionStrategy;
import com.aksesi.application.element.Character;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.element.Password;
import com.aksesi.application.element.PasswordElement;
import com.aksesi.application.encrypter.PasswordEncrypter;
import com.aksesi.application.shape.direction.IDirectionResolver;
import com.aksesi.application.shape.direction.LineDirectionResolver;
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

    PasswordEncrypter passwordEncrypter;
    AbstractConverter gestureElementConverter;
    AbstractConverter characterElementConverter;

    IConversionStrategy linearConversionStrategy;
    IDirectionResolver lineDirectionResolver;

    Password password;

    @Before
    public void setup() {
        lineDirectionResolver = new LineDirectionResolver();
        linearConversionStrategy = new LinearConversionStrategy(lineDirectionResolver);

        gestureElementConverter = new GestureConverter(linearConversionStrategy);
        characterElementConverter = new CharacterConverter();

        passwordEncrypter = new PasswordEncrypter();
        passwordEncrypter
                .registerConverter(gestureElementConverter)
                .registerConverter(characterElementConverter);
    }


    @Test
    public void emptyPasswordTest() {
        setupPassword(

        );

        String result = passwordEncrypter.encrypt(password);

        assertTrue(result.isEmpty());
    }

    @Test
    public void onlyCharacterElementTest() {
        setupPassword(
                new Character('a')
        );

        String result = passwordEncrypter.encrypt(password);

        assertEquals("a", result);

    }

    @Test
    public void onlyGestureElementTest() {
        setupPassword(
                new Gesture(Arrays.asList(
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(2L, 2L)
                ))
        );

        String result = passwordEncrypter.encrypt(password);

        assertEquals("LineDIAGONAL_RIGHT", result);
    }

    @Test
    public void combineGesturesAndCharactersElementTest1() {
        setupPassword(
                new Gesture(Arrays.asList( //LineDIAGONAL_RIGHT
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(2L, 2L)
                )),
                new Character('a'), //a
                new Character('b'), //b
                new Character('d'), //d
                new Gesture(Arrays.asList( //LineDIAGONAL_LEFT
                        new Gesture.Point(1L, 2L),
                        new Gesture.Point(2L, 1L)
                ))

        );

        String result = passwordEncrypter.encrypt(password);

        assertEquals("LineDIAGONAL_RIGHTabdLineDIAGONAL_LEFT", result);
    }

    @Test
    public void combineGesturesAndCharactersElementTest2() {
        setupPassword(
                new Gesture(Arrays.asList( //LineDIAGONAL_RIGHT
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(2L, 2L)
                )),
                new Character('a'), //a
                new Gesture(Arrays.asList( //LineDIAGONAL_VETRICAL
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(1L, 2L)
                )),
                new Character('b'), //b
                new Gesture(Arrays.asList( //LineHORIZONTAL
                        new Gesture.Point(1L, 1L),
                        new Gesture.Point(2L, 1L)
                )),
                new Character('\\'), // \
                new Character('$') //d


        );

        String result = passwordEncrypter.encrypt(password);

        assertEquals("LineDIAGONAL_RIGHTaLineVERTICALbLineHORIZONTAL\\$".length(), result.length());
        assertEquals("LineDIAGONAL_RIGHTaLineVERTICALbLineHORIZONTAL\\$", result);
    }

    @Test
    public void specialCharactersTest2() {
        setupPassword(
                new Character('/'), // /
                new Character('$'), // $
                new Character('\\'), // \
                new Character('~'), // ~
                new Character('`') // `


        );

        String result = passwordEncrypter.encrypt(password);

        assertEquals("/$\\~`".length(), result.length());
        assertEquals("/$\\~`", result);
    }


    private void setupPassword(PasswordElement...elements) {
        List<PasswordElement> passwordElements = Arrays.asList(elements);
        password = new Password(passwordElements);
    }
}
