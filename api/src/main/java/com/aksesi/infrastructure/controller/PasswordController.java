package com.aksesi.infrastructure.controller;

import com.aksesi.application.element.Password;
import com.aksesi.infrastructure.dto.AuthenticationRequestDTO;
import com.aksesi.application.encrypter.PasswordEncrypter;
import com.aksesi.infrastructure.builder.AuthenticationRequestBuilder;
import com.aksesi.infrastructure.builder.BuildingAuthenticationRequestException;
import com.aksesi.infrastructure.response.MessageResponse;
import com.aksesi.infrastructure.template.AksesiRestTemplateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/password")
public class PasswordController {

    @Autowired
    private PasswordEncrypter passwordConverter;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private AuthenticationRequestBuilder authenticationRequestBuilder;

    @Autowired
    private AksesiRestTemplateProvider restTemplateProvider;

    @PostMapping
    public @ResponseBody
    ResponseEntity<MessageResponse> decodePassword(@RequestBody AuthenticationRequestDTO request) {
        Password passwordToConvert = conversionService.convert(request.getPassword(), Password.class);

        //convert password
        String result = passwordConverter.encrypt(passwordToConvert);

        //using the request (especially its configuration) and converted password prepare an authentication request
        HttpEntity<String> authenticationRequest;
        try {
            authenticationRequest = authenticationRequestBuilder.build(request, result);
        } catch (BuildingAuthenticationRequestException e) {
            return new ResponseEntity<MessageResponse>(new MessageResponse("Invalid form data"), HttpStatus.BAD_REQUEST);
        }

        //hit authentication endpoint and wait for a response
        RestTemplate template = restTemplateProvider.getTemplate();
        ResponseEntity<String> response = template.postForEntity(request.getConfiguration().getUrl(), authenticationRequest, String.class);

        //without touching response pass it into the requester
        return new ResponseEntity<MessageResponse>(new MessageResponse(response.getBody()), response.getStatusCode());
    }
}
