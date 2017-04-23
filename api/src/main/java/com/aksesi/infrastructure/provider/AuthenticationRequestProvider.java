package com.aksesi.infrastructure.provider;

import com.aksesi.infrastructure.dto.AuthenticationRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 23/04/2017.
 */
//TODO mbrycki - should it be moved into application branch?
@Component
public class AuthenticationRequestProvider {

    public HttpEntity<MultiValueMap<String, String>> prepareRequest(AuthenticationRequestDTO request, String convertedPassword) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(request.getConfiguration().getInputConfiguration().getLoginName(), request.getLogin());
        requestBody.add(request.getConfiguration().getInputConfiguration().getPasswordName(), convertedPassword);

        HttpEntity<MultiValueMap<String, String>> authenticationRequest = new HttpEntity<>(requestBody, headers);

        return authenticationRequest;
    }
}
