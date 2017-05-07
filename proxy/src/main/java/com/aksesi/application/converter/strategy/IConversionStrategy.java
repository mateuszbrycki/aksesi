package com.aksesi.application.converter.strategy;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Shape;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public interface IConversionStrategy {

    Shape convert(Gesture element) throws ConversionException;
}
