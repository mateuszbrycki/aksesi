package com.aksesi.application.converter;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.element.PasswordElement;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public abstract class AbstractConverter<T extends PasswordElement> {

    protected Class<T> convertedClass;

    protected AbstractConverter(Class<T> convertedClass) {
        this.convertedClass = convertedClass;
    }

    public Class<T> converts() {
        return convertedClass;
    }

    public abstract String convert(T element) throws ConversionException;
}
