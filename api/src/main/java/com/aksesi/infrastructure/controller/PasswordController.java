package com.aksesi.infrastructure.controller;

import com.aksesi.application.element.Password;
import com.aksesi.infrastructure.dto.PasswordDTO;
import com.aksesi.application.encrypter.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@RestController
@RequestMapping(path = "/password")
public class PasswordController {

    @Autowired
    private PasswordEncrypter passwordConverter;

    @Autowired
    private ConversionService conversionService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<String> decodePassword(@RequestBody PasswordDTO password) {

        Password passwordToConvert = conversionService.convert(password, Password.class);

        String result = passwordConverter.encrypt(passwordToConvert);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
