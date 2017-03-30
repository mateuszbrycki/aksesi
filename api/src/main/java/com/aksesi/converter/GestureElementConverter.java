package com.aksesi.converter;

import com.aksesi.converter.exception.ConversionException;
import com.aksesi.converter.strategy.IConversionStrategy;
import com.aksesi.element.GestureElement;
import com.aksesi.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Component
public class GestureElementConverter extends AbstractConverter<GestureElement> {

    private IConversionStrategy conversionStrategy;

    public GestureElementConverter(@Autowired IConversionStrategy strategy) {
        super(GestureElement.class);
        this.conversionStrategy = strategy;
    }

    @Override
    public String convert(GestureElement element) throws ConversionException {
        Shape shape = conversionStrategy.convert(element);

        return shape.toString();
    }


}
