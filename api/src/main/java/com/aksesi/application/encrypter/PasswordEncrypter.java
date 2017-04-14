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

    public String encrypt(final Password password) {
        Collection<PasswordElement> passwordElements = password.getElements();

        final StringBuilder resultBuilder = new StringBuilder();

        final Function<PasswordElement, String> conversionFunction = (PasswordElement element) -> {
            AbstractConverter converter = converterMap.get(element.getClass());

            try {
                return converter.convert(element);
            } catch (ConversionException e) {
                return null;
            }
        };

        passwordElements.stream()
                .filter((e) -> converterMap.get(e.getClass()) != null )
                .map(conversionFunction)
                .forEach(resultBuilder::append);

        return resultBuilder.toString();
    }

}

