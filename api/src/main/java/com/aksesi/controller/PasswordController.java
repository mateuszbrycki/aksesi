package com.aksesi.controller;

import com.aksesi.element.Password;
import com.aksesi.element.dto.PasswordDTO;
import com.aksesi.service.PasswordConversionService;
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
    private PasswordConversionService passwordConverter;

    @Autowired
    private ConversionService conversionService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<String> decodePassword(@RequestBody PasswordDTO password) {

        Password passwordToConvert = conversionService.convert(password, Password.class);

        String result = passwordConverter.process(passwordToConvert);

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
