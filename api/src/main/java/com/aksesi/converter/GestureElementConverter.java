package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.converter.strategy.IConversionStrategy;
import com.aksesi.element.GestureElement;
import com.aksesi.shape.Shape;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class GestureElementConverter extends AbstractConverter<GestureElement> {

    private IConversionStrategy conversionStrategy;

    public GestureElementConverter(IConversionStrategy strategy) {
        this.conversionStrategy = strategy;
    }

    @Override
    public String convert(GestureElement element) throws ConversionException {
        Shape shape = conversionStrategy.convert(element);

        return shape.toString();
    }


}
