package com.aksesi;

import com.aksesi.infrastructure.dto.*;
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

import java.util.Arrays;

import static com.aksesi.utils.ResponseUtils.prepareResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AksesiContextApplicationTests {

    private final static String TEST_LOGIN = "TEST";

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
        AuthenticationRequestDTO request = setupPassword(
                new CharacterDTO('a')
        );

        String requestJson = convertToJSON(request);

        mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(prepareResponse("a")));
    }

    @Test
    public void testHttpStatusWithCharacterAndGesture() throws Exception {
        AuthenticationRequestDTO request = setupPassword(
                new GestureDTO(Arrays.asList(
                        new PointDTO(1L,1L),new PointDTO(2L,2L)
                ))
        );

        String requestJson = convertToJSON(request);

        mockMvc.perform(post("/password")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(prepareResponse("LineDIAGONAL_RIGHT")));
    }

    @Test
    public void testHttpStatusWithMixedPassword() throws Exception {
        AuthenticationRequestDTO request = setupPassword(
                new CharacterDTO('a'),
                new GestureDTO(Arrays.asList(
                    new PointDTO(1L,1L),new PointDTO(2L,2L)
                ))
        );

        String requestJson = convertToJSON(request);

        mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(prepareResponse("aLineDIAGONAL_RIGHT")));
    }

    private AuthenticationRequestDTO setupPassword(PasswordElementDTO...elements) {

        PasswordDTO password = new PasswordDTO(Arrays.asList(elements));
        ConfigurationDTO configuration = new ConfigurationDTO();
        AuthenticationRequestDTO request = new AuthenticationRequestDTO(TEST_LOGIN, password, configuration);

        return request;
    }

    private String convertToJSON(Object value) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }
}
