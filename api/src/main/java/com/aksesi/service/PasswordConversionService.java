package com.aksesi.service;

import com.aksesi.converter.AbstractConverter;
import com.aksesi.converter.exception.ConversionException;
import com.aksesi.element.PasswordElement;
import com.aksesi.element.Password;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public class PasswordConversionService {

    Map<Class, AbstractConverter> converterMap = new HashMap<>();

    public PasswordConversionService() {}

    public PasswordConversionService registerConverter(AbstractConverter converter) {
        converterMap.put(
                converter.converts(),
                converter
        );

        return this;
    }

    public Collection<AbstractConverter> getConverters() {

        List<AbstractConverter> result = converterMap.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());

        return result;
    }

    //TODO mbrycki repair!!!!!!!!!!!!!!!!!!!!!!
    public void removeAllConverters() {
        converterMap = new HashMap<>();
        System.gc();
    }

    public String process(Password password) {
        Collection<PasswordElement> passwordElements = password.getElements();

        StringBuilder resultBuilder = new StringBuilder();

        passwordElements.forEach(e -> {
            //TODO mbrycki what should we do if converter is not registered?
            AbstractConverter converter = converterMap.get(e.getClass());
            try {
                resultBuilder.append(converter.convert(e));
            } catch(ConversionException exception) {
                exception.printStackTrace();
            }
        });
        return resultBuilder.toString();
    }


}
