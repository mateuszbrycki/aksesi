package com.aksesi;

import com.aksesi.infrastructure.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mateusz Brycki on 30/03/2017.
 */
@RunWith(Parameterized.class)
@SpringBootTest
@WebAppConfiguration
public class AksesiContextApplicationTestParametrized {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String expected;
    private List<PasswordElementDTO> elements;

    public AksesiContextApplicationTestParametrized(List<PasswordElementDTO> elements, String expected) {
        this.elements = elements;
        this.expected = expected;
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(new CharacterDTO('a')), "a"},
                {Arrays.asList(new GestureDTO(Arrays.asList(
                        new PointDTO(1L, 1L), new PointDTO(2L, 2L)
                ))), "LineDIAGONAL_RIGHT"},
                {Arrays.asList(new CharacterDTO('a'), new GestureDTO(Arrays.asList(
                        new PointDTO(1L, 1L), new PointDTO(2L, 2L)
                ))), "aLineDIAGONAL_RIGHT"}
        });
    }

    @Test
    public void test() throws Exception {
        PasswordDTO password = setupPassword(elements);

        String requestJson = convertToJSON(password);

        mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    private PasswordDTO setupPassword(List<PasswordElementDTO> elements) {

        PasswordDTO password = new PasswordDTO(elements);
        return password;
    }

    private String convertToJSON(Object value) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }
}
