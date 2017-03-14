package com.aksesi.password;

import com.aksesi.Password;
import com.aksesi.element.CharacterElement;
import com.aksesi.element.PasswordElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class PasswordTests {

    @Test
    public void emptyPasswordLengthTest() {
        List<PasswordElement> objects = Arrays.asList();
        Password password = new Password(objects);

        assertEquals(0, password.length());
    }

    @Test
    public void notEmptyPasswordLengthTest() {
        List<PasswordElement> objects = Arrays.asList(
                mock(PasswordElement.class),
                mock(PasswordElement.class),
                mock(PasswordElement.class)
        );
        Password password = new Password(objects);

        assertEquals(objects.size(), password.length());
    }

    @Test
    public void passwordElementsTest() {
        List<PasswordElement> objects = Arrays.asList(
                mock(PasswordElement.class),
                mock(PasswordElement.class),
                mock(PasswordElement.class)
        );
        Password password = new Password(objects);

        assertEquals(objects.size(), password.length());
        assertSame(objects, password.getElements());
    }




}
