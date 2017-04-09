package com.aksesi.infrastructure.controller;

import com.aksesi.application.element.Password;
import com.aksesi.infrastructure.dto.PasswordDTO;
import com.aksesi.application.encrypter.PasswordEncrypter;
import com.aksesi.infrastructure.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public @ResponseBody
    ResponseEntity<MessageResponse> decodePassword(@RequestBody PasswordDTO password) {

        Password passwordToConvert = conversionService.convert(password, Password.class);

        String result = passwordConverter.encrypt(passwordToConvert);

        return new ResponseEntity<MessageResponse>(new MessageResponse(result), HttpStatus.OK);
    }
}
