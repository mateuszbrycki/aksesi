package com.aksesi.infrastructure.builder;

import com.aksesi.infrastructure.dto.AuthenticationRequestDTO;
import com.aksesi.infrastructure.dto.ConfigurationDTO;
import com.aksesi.infrastructure.dto.InputConfiguration;
import com.aksesi.infrastructure.dto.PasswordDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 23/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationRequestBuilderTests {

    private final String CONVERTED_PASSWORD = "CONVERTED_PASSWORD";
    private final String LOGIN = "LOGIN";

    private final String LOGIN_INPUT_NAME = "LOGIN_INPUT_NAME";
    private final String PASSWORD_INPUT_NAME = "PASSWORD_INPUT_NAME";

    private AuthenticationRequestBuilder builder;

    @Mock
    PasswordDTO passwordDTO;

    @Mock
    ConfigurationDTO configurationDTO;

    @Mock
    InputConfiguration inputConfiguration;

    @Before
    public void setupAuthenticationRequestProvider() {
        builder = new AuthenticationRequestBuilder();

        when(configurationDTO.getInputConfiguration()).thenReturn(inputConfiguration);
    }

    @Test(expected = BuildingAuthenticationRequestException.class)
    public void requestShouldContainContentTypeHeaderTest() throws BuildingAuthenticationRequestException {

        HttpEntity<String> request = builder.build(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);
    }

    @Test(expected = BuildingAuthenticationRequestException.class)
    public void bodyShouldNotContainLoginAndPasswordTest() throws BuildingAuthenticationRequestException {
        HttpEntity<String> request = builder.build(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);
    }

    @Test
    public void bodyShouldContainLoginAndPasswordTest() throws BuildingAuthenticationRequestException, JSONException {
        when(inputConfiguration.getLoginName()).thenReturn(LOGIN_INPUT_NAME);
        when(inputConfiguration.getPasswordName()).thenReturn(PASSWORD_INPUT_NAME);

        HttpEntity<String> request = builder.build(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);

        String body = request.getBody();
        String expectedBody = buildExpectedRequest(Collections.emptyMap()).getBody();

        assertEquals(expectedBody, body);
        assertTrue(body.contains("\"" + LOGIN + "\""));
        assertTrue(body.contains("\"" + CONVERTED_PASSWORD + "\""));
    }

    @Test
    public void bodyShouldContainRequestHeaders() throws BuildingAuthenticationRequestException, JSONException {
        when(inputConfiguration.getLoginName()).thenReturn(LOGIN_INPUT_NAME);
        when(inputConfiguration.getPasswordName()).thenReturn(PASSWORD_INPUT_NAME);
        Map<String, String> headers = buildRequestHeaders();
        when(configurationDTO.getHeaders()).thenReturn(headers);

        HttpEntity<String> request = builder.build(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);

        assertEquals(buildExpectedRequest(headers), request);
    }

    private AuthenticationRequestDTO prepareAuthenticationRequestDTO() {
        return new AuthenticationRequestDTO(LOGIN, passwordDTO, configurationDTO);
    }

    private Map<String, String> buildRequestHeaders() {
        Map<String, String> headers = new HashMap<>();

        headers.put("TEST_HEADER_1", "TEST_HEADER_1_VALUE");
        headers.put("TEST_HEADER_2", "TEST_HEADER_2_VALUE");

        return headers;
    }

    private HttpEntity<String> buildExpectedRequest(Map<String, String> headers) throws JSONException {
        JSONObject expectedBody = new JSONObject();
        expectedBody.put(LOGIN_INPUT_NAME, LOGIN);
        expectedBody.put(PASSWORD_INPUT_NAME, CONVERTED_PASSWORD);

        MultiValueMap<String, String> expectedHeaders = new LinkedMultiValueMap<>();
        expectedHeaders.setAll(headers);

        return new HttpEntity<String>(expectedBody.toString(), expectedHeaders);
    }


}
