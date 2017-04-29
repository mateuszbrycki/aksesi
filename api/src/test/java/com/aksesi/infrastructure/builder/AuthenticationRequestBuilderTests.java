package com.aksesi.infrastructure.builder;

import com.aksesi.infrastructure.dto.AuthenticationRequestDTO;
import com.aksesi.infrastructure.dto.ConfigurationDTO;
import com.aksesi.infrastructure.dto.InputConfiguration;
import com.aksesi.infrastructure.dto.PasswordDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;

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
    public void bodyShouldContainLoginAndPasswordTest() throws BuildingAuthenticationRequestException {
        when(inputConfiguration.getLoginName()).thenReturn(LOGIN_INPUT_NAME);
        when(inputConfiguration.getPasswordName()).thenReturn(PASSWORD_INPUT_NAME);

        HttpEntity<String> request = builder.build(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);

        String body = request.getBody();
        assertEquals("{\"PASSWORD_INPUT_NAME\":\"CONVERTED_PASSWORD\",\"LOGIN_INPUT_NAME\":\"LOGIN\"}", body);
        assertTrue(body.contains("\"" + LOGIN + "\""));
        assertTrue(body.contains("\"" + CONVERTED_PASSWORD + "\""));
    }

    private AuthenticationRequestDTO prepareAuthenticationRequestDTO() {
        return new AuthenticationRequestDTO(LOGIN, passwordDTO, configurationDTO);
    }

}
