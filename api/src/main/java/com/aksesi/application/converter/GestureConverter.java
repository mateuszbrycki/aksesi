package com.aksesi.application.converter;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.converter.strategy.IConversionStrategy;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Component
public class GestureConverter extends AbstractConverter<Gesture> {

    private IConversionStrategy conversionStrategy;

    public GestureConverter(@Autowired IConversionStrategy strategy) {
        super(Gesture.class);
        this.conversionStrategy = strategy;
    }

    @Override
    public String convert(Gesture element) throws ConversionException {
        Shape shape = conversionStrategy.convert(element);

        return shape.toString();
    }


}
