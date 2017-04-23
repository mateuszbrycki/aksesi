package com.aksesi.infrastructure.provider;

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
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 23/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationRequestProviderTests {

    private final String CONVERTED_PASSWORD = "CONVERTED_PASSWORD";
    private final String LOGIN = "LOGIN";

    private final String LOGIN_INPUT_NAME = "LOGIN_INPUT_NAME";
    private final String PASSWORD_INPUT_NAME = "PASSWORD_INPUT_NAME";

    private AuthenticationRequestProvider provider;

    @Mock
    PasswordDTO passwordDTO;

    @Mock
    ConfigurationDTO configurationDTO;

    @Mock
    InputConfiguration inputConfiguration;

    @Before
    public void setupAuthenticationRequestProvider() {
        provider = new AuthenticationRequestProvider();

        when(configurationDTO.getInputConfiguration()).thenReturn(inputConfiguration);
    }

    @Test
    public void requestShouldContainContentTypeHeaderTest() {
        when(inputConfiguration.getLoginName()).thenReturn(null);
        when(inputConfiguration.getPasswordName()).thenReturn(null);

        HttpEntity<MultiValueMap<String, String>> request = provider.prepareRequest(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);

        String headerValue = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        assertEquals("application/json", headerValue);
    }

    @Test
    public void bodyShouldNotContainLoginAndPasswordTest() {
        HttpEntity<MultiValueMap<String, String>> request = provider.prepareRequest(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);

        String loginValue = request.getBody().getFirst(LOGIN_INPUT_NAME);
        assertEquals(null, loginValue);

        String passwordValue = request.getBody().getFirst(PASSWORD_INPUT_NAME);
        assertEquals(null, passwordValue);
    }

    @Test
    public void bodyShouldContainLoginAndPasswordTest() {
        when(inputConfiguration.getLoginName()).thenReturn(LOGIN_INPUT_NAME);
        when(inputConfiguration.getPasswordName()).thenReturn(PASSWORD_INPUT_NAME);

        HttpEntity<MultiValueMap<String, String>> request = provider.prepareRequest(prepareAuthenticationRequestDTO(), CONVERTED_PASSWORD);

        String loginValue = request.getBody().getFirst(LOGIN_INPUT_NAME);
        assertEquals(LOGIN, loginValue);

        String passwordValue = request.getBody().getFirst(PASSWORD_INPUT_NAME);
        assertEquals(CONVERTED_PASSWORD, passwordValue);
    }

    private AuthenticationRequestDTO prepareAuthenticationRequestDTO() {
        return new AuthenticationRequestDTO(LOGIN, passwordDTO, configurationDTO);
    }

}
