package com.aksesi.configuration;

import com.aksesi.converter.CharacterElementConverter;
import com.aksesi.converter.GestureElementConverter;
import com.aksesi.service.PasswordConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Configuration
public class PasswordConversionServiceConfiguration {

    //TODO mbrycki find more elegant way
    @Bean
    public PasswordConversionService passwordConversionService(@Autowired CharacterElementConverter charConv, @Autowired GestureElementConverter getConv) {
        PasswordConversionService service = new PasswordConversionService();

        service.registerConverter(charConv);
        service.registerConverter(getConv);

        return service;
    }

}
