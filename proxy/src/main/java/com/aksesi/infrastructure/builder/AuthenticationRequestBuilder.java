package com.aksesi.infrastructure.builder;

import com.aksesi.infrastructure.dto.AuthenticationRequestDTO;
import com.aksesi.infrastructure.dto.InputConfiguration;
import com.aksesi.infrastructure.logger.AksesiLogger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by Mateusz Brycki on 23/04/2017.
 */
//TODO mbrycki - should it be moved into application branch?
@Component
public class AuthenticationRequestBuilder {

    private static AksesiLogger log = AksesiLogger.getLogger(AuthenticationRequestBuilder.class.getName());

    public HttpEntity<String> build(AuthenticationRequestDTO request, String convertedPassword)
        throws BuildingAuthenticationRequestException
    {
        log.info("Building authentication request with password " + convertedPassword);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.setAll(request.getConfiguration().getHeaders());

        JSONObject requestBody = new JSONObject();
        try {
            InputConfiguration inputConfiguration = request.getConfiguration().getInputConfiguration();
            requestBody.put(inputConfiguration.getLoginName(), request.getLogin());
            requestBody.put(inputConfiguration.getPasswordName(), convertedPassword);
        } catch (JSONException e) {
            throw new BuildingAuthenticationRequestException(e.getMessage());
        }

        HttpEntity<String> authenticationRequest = new HttpEntity<>(requestBody.toString(), headers);

        log.info("Built request " + authenticationRequest);
        return authenticationRequest;
    }
}
