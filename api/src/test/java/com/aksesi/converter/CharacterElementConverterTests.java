package com.aksesi.converter;

import com.aksesi.element.CharacterElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterElementConverterTests {

    @Mock
    private CharacterElement characterElement;

    private final Character TEST_CHARACTER = 'a';
    private final Character TEST_CHARACTER_2 = 'b';

    @Test
    public void convertedClassTest() {
        CharacterElementConverter converter = new CharacterElementConverter();

        assertEquals(CharacterElement.class, converter.converts());
    }

    @Test
    public void convertOneCharacterTest() {
        CharacterElementConverter converter = new CharacterElementConverter();

        when(characterElement.getCharacter()).thenReturn(TEST_CHARACTER);

        String result = converter.convert(characterElement);

        assertEquals(TEST_CHARACTER.toString(), result);
    }

    @Test
    public void convertTwoCharactersTest() {
        CharacterElementConverter converter = new CharacterElementConverter();

        when(characterElement.getCharacter())
                .thenReturn(TEST_CHARACTER)
                .thenReturn(TEST_CHARACTER_2);

        String result = converter.convert(characterElement);

        assertEquals(TEST_CHARACTER.toString(), result);

        result = converter.convert(characterElement);

        assertEquals(TEST_CHARACTER_2.toString(), result);
    }


}
