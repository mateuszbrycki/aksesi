package com.aksesi;

import com.aksesi.element.dto.PointDTO;
import com.aksesi.element.dto.GestureElementDTO;
import com.aksesi.element.dto.PasswordElementDTO;
import com.aksesi.element.dto.CharacterElementDTO;
import com.aksesi.element.dto.PasswordDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AksesiApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testHttpStatusWithCharacter() throws Exception {
        PasswordDTO password = setupPassword(
                new CharacterElementDTO('a')
        );

        String requestJson = convertToJSON(password);

        mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("a"));
    }

    @Test
    public void testHttpStatusWithCharacterAndGesture() throws Exception {
        PasswordDTO password = setupPassword(
                new GestureElementDTO(Arrays.asList(
                        new PointDTO(1L,1L),new PointDTO(2L,2L)
                ))
        );

        String requestJson = convertToJSON(password);

        mockMvc.perform(post("/password")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("LineDIAGONAL_RIGHT"));
    }

    @Test
    public void testHttpStatusWithMixedPassword() throws Exception {
        PasswordDTO password = setupPassword(
                new CharacterElementDTO('a'),
                new GestureElementDTO(Arrays.asList(
                    new PointDTO(1L,1L),new PointDTO(2L,2L)
                ))
        );

        String requestJson = convertToJSON(password);

        mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .andExpect(status().isOk())
                .andExpect(content().string("aLineDIAGONAL_RIGHT"));
    }

    private PasswordDTO setupPassword(PasswordElementDTO...elements) {

        PasswordDTO password = new PasswordDTO(Arrays.asList(elements));
        return password;
    }

    private String convertToJSON(Object value) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }


}