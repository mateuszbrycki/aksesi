package com.aksesi.application.encrypter;

import com.aksesi.application.converter.AbstractConverter;
import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.element.PasswordElement;
import com.aksesi.application.element.Password;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
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
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return result;
    }

    public void removeAllConverters() {
        converterMap = new HashMap<>();
        System.gc();
    }

    private final Function<PasswordElement, Optional<String>> conversionFunction = (PasswordElement element) -> {
        AbstractConverter converter = converterMap.get(element.getClass());

        try {
            String convert = converter.convert(element);
            return Optional.of(convert);
        } catch (ConversionException e) {
            return Optional.empty();
        }
    };

    public String encrypt(final Password password) {
        return password.getElements().stream()
                .filter((e) -> converterMap.get(e.getClass()) != null )
                .map(conversionFunction)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining());
    }

}

