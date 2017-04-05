package com.aksesi.application.encrypter;

import com.aksesi.application.converter.AbstractConverter;
import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.element.PasswordElement;
import com.aksesi.application.element.Password;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@Component
public class PasswordEncrypter {

    private Map<Class, AbstractConverter> converterMap = new HashMap<>();

    public PasswordEncrypter registerConverter(AbstractConverter converter) {
        converterMap.put(
                converter.converts(),
                converter
        );

        return this;
    }

    public Collection<AbstractConverter> converters() {

        List<AbstractConverter> result = converterMap.entrySet().stream()
                .map(x -> x.getValue())
                .collect(Collectors.toList());

        return result;
    }

    public void removeAllConverters() {
        converterMap = new HashMap<>();
        System.gc();
    }

    public String encrypt(Password password) {
        Collection<PasswordElement> passwordElements = password.getElements();

        StringBuilder resultBuilder = new StringBuilder();

        passwordElements.forEach(e -> {

            AbstractConverter converter = converterMap.get(e.getClass());
            if(converter == null) { //if there is no converter registered then skip current element
                return;
            }

            try {
                resultBuilder.append(converter.convert(e));
            } catch(ConversionException exception) {
                exception.printStackTrace();
            }
        });

        return resultBuilder.toString();
    }
}
