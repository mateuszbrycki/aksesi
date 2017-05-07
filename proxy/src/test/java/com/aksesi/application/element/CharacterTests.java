package com.aksesi.application.element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class CharacterTests {

    private final java.lang.Character TEST_CHARACTER = 'a';

    @Test
    public void getCharacterTest() {
        Character element = new Character(TEST_CHARACTER);

        assertEquals(TEST_CHARACTER, element.getCharacter());
    }

}
