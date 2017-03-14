package com.aksesi.element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class CharacterElementTests {

    private final Character TEST_CHARACTER = 'a';

    @Test
    public void getCharacterTest() {
        CharacterElement element = new CharacterElement(TEST_CHARACTER);

        assertEquals(TEST_CHARACTER, element.getCharacter());
    }

}
