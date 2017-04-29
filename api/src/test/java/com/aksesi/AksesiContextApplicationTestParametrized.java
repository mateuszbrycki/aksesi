package com.aksesi;

import com.aksesi.infrastructure.dto.*;
import com.aksesi.infrastructure.template.AksesiRestTemplateProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.aksesi.utils.ResponseUtils.prepareResponse;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
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

    private final static String TEST_LOGIN = "TEST";
    private final static String URL = "http://test.url";
    private final static HttpMethod METHOD = HttpMethod.POST;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private AksesiRestTemplateProvider provider;

    private String expected;
    private List<PasswordElementDTO> elements;
    private ResponseCreator endpointResponse;

    public AksesiContextApplicationTestParametrized(List<PasswordElementDTO> elements, String expected, ResponseCreator endpointResponse) {
        this.elements = elements;
        this.expected = expected;
        this.endpointResponse = endpointResponse;
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        mockRestServiceServer = MockRestServiceServer.createServer(provider.getTemplate());
    }

    @Parameters(name = "Expected {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(new CharacterDTO('a')), "a", withSuccess("a", MediaType.APPLICATION_JSON)},
                {Arrays.asList(new GestureDTO(Arrays.asList(
                        new PointDTO(1L, 1L), new PointDTO(2L, 2L)
                ))), "LineDIAGONAL_RIGHT", withSuccess("LineDIAGONAL_RIGHT", MediaType.APPLICATION_JSON)},
                {Arrays.asList(new CharacterDTO('a'), new GestureDTO(Arrays.asList(
                        new PointDTO(1L, 1L), new PointDTO(2L, 2L)
                ))), "aLineDIAGONAL_RIGHT", withSuccess("aLineDIAGONAL_RIGHT", MediaType.APPLICATION_JSON)}
        });
    }

    @Test
    public void test() throws Exception {

        mockRestServiceServer.expect(
                requestTo(URL))
                .andExpect(method(METHOD))
                .andRespond(endpointResponse);

        AuthenticationRequestDTO request = setupPassword(elements);

        String requestJson = convertToJSON(request);

        mockMvc.perform(post("/password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .andExpect(status().isOk())
                .andExpect(content().json(prepareResponse(expected)));
    }

    private AuthenticationRequestDTO setupPassword(List<PasswordElementDTO> elements) {

        PasswordDTO password = new PasswordDTO(elements);

        InputConfiguration inputConfiguration = new InputConfiguration("login", "password");

        ConfigurationDTO configuration = new ConfigurationDTO(inputConfiguration, URL, METHOD.name());

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
