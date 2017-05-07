package com.aksesi.infrastructure.converter;

import com.aksesi.application.element.Character;
import com.aksesi.application.element.Gesture;
import com.aksesi.infrastructure.dto.CharacterDTO;
import com.aksesi.infrastructure.dto.GestureDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by mbrycki on 2017-04-05.
 */
@RunWith(MockitoJUnitRunner.class)
public class TargetClassProviderTests
{
    TargetClassProvider classProvider;

    @Before
    public void setupClassProvider() {
        classProvider = new TargetClassProvider();
    }

    @Test
    public void checkNullClassTest() {
        classProvider.register(null, null);

        assertNull(classProvider.getTargetType(null));
    }

    @Test
    public void checkOneRegisteredClassTest() {
        classProvider.register(GestureDTO.class, Gesture.class);

        Class<?> targetClass = classProvider.getTargetType(GestureDTO.class);
        assertNotNull(targetClass);
        assertEquals(targetClass, Gesture.class);
    }

    @Test
    public void checkTwoRegisteredClassTest() {
        classProvider.register(GestureDTO.class, Gesture.class);
        classProvider.register(CharacterDTO.class, Character.class);

        Class<?> targetClass = classProvider.getTargetType(GestureDTO.class);
        assertNotNull(targetClass);
        assertEquals(targetClass, Gesture.class);

        targetClass = classProvider.getTargetType(CharacterDTO.class);
        assertNotNull(targetClass);
        assertEquals(targetClass, Character.class);
    }

}
