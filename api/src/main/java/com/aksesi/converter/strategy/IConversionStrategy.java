package com.aksesi.converter.strategy;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.element.GestureElement;
import com.aksesi.shape.Shape;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public interface IConversionStrategy {

    Shape convert(GestureElement element) throws ConversionException;
}
