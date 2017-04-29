package com.aksesi.application.converter;

import com.aksesi.application.element.Character;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterConverterTests {

    @Mock
    private Character character;

    private final java.lang.Character TEST_CHARACTER = 'a';
    private final java.lang.Character TEST_CHARACTER_2 = 'b';

    @Test
    public void convertedClassTest() {
        CharacterConverter converter = new CharacterConverter();

        assertEquals(Character.class, converter.converts());
    }

    @Test
    public void convertOneCharacterTest() {
        CharacterConverter converter = new CharacterConverter();

        when(character.getCharacter()).thenReturn(TEST_CHARACTER);

        String result = converter.convert(character);

        assertEquals(TEST_CHARACTER.toString(), result);
    }

    @Test
    public void convertTwoCharactersTest() {
        CharacterConverter converter = new CharacterConverter();

        when(character.getCharacter())
                .thenReturn(TEST_CHARACTER)
                .thenReturn(TEST_CHARACTER_2);

        String result = converter.convert(character);

        assertEquals(TEST_CHARACTER.toString(), result);

        result = converter.convert(character);

        assertEquals(TEST_CHARACTER_2.toString(), result);
    }

}
