package com.aksesi.infrastructure.controller;

import com.aksesi.application.element.Password;
import com.aksesi.infrastructure.dto.AuthenticationRequestDTO;
import com.aksesi.infrastructure.dto.PasswordDTO;
import com.aksesi.application.encrypter.PasswordEncrypter;
import com.aksesi.infrastructure.provider.AuthenticationRequestProvider;
import com.aksesi.infrastructure.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    private AuthenticationRequestProvider authenticationRequestProvider;

    @PostMapping
    public @ResponseBody
    ResponseEntity<MessageResponse> decodePassword(@RequestBody AuthenticationRequestDTO request) {
        Password passwordToConvert = conversionService.convert(request.getPassword(), Password.class);

        //convert password
        String result = passwordConverter.encrypt(passwordToConvert);

        //using the request (especially its configuration) and converted password prepare an authentication request
        HttpEntity<MultiValueMap<String, String>> authenticationRequest = authenticationRequestProvider.prepareRequest(request, result);

        //hit authentication endpoint and wait for a response
//        ResponseEntity<String> response = new RestTemplate().postForEntity(request.getConfiguration().getUrl(), authenticationRequest, String.class);

        //without touching response pass it into the requester
        return new ResponseEntity<MessageResponse>(new MessageResponse(result), HttpStatus.OK);
    }
}
