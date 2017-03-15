package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.element.PasswordElement;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public abstract class AbstractConverter<T extends PasswordElement> {

    public final Class converts() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    abstract String convert(T element) throws ConversionException;
}
