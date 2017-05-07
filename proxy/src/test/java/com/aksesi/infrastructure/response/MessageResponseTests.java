package com.aksesi.infrastructure.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 14/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageResponseTests {

    private MessageResponse response;

    private static final String TEST_MESSAGE = "TEST_MESSAGE";

    @Test
    public void settingMessageByConstructorTest() {
        response = new MessageResponse(TEST_MESSAGE);

        assertEquals(TEST_MESSAGE, response.getMessage());
    }

    @Test
    public void settingMessageBySetterTest() {
        response = new MessageResponse(TEST_MESSAGE);

        String message = TEST_MESSAGE + "_NEW";

        response.setMessage(message);

        assertEquals(message, response.getMessage());
    }

}
