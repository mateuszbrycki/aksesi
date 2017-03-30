package com.aksesi.element.converter;


import com.aksesi.element.CharacterElement;
import com.aksesi.element.GestureElement;
import com.aksesi.element.Password;
import com.aksesi.element.PasswordElement;
import com.aksesi.element.dto.GestureElementDTO;
import com.aksesi.element.dto.PasswordDTO;
import com.aksesi.element.dto.PasswordElementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class PasswordDTOConverter implements Converter<PasswordDTO, Password> {

    private ConversionService conversionService;

    public PasswordDTOConverter(@Autowired @Lazy ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Password convert(PasswordDTO passwordDTO) {

        List<PasswordElement> elements = convertElements(passwordDTO.getElements());
        return new Password(elements);
    }

    private List<PasswordElement> convertElements(Collection<PasswordElementDTO> elements) {
        List<PasswordElement> result = new ArrayList<>();

        //TODO mbrycki refactor
        elements.forEach(e -> {
            Class clazz = null;
            if(e instanceof GestureElementDTO) {
                clazz = GestureElement.class;
            } else {
                clazz = CharacterElement.class;
            }

            Object element = conversionService.convert(e, clazz);

            result.add((PasswordElement)element);
        });

        return result;
    }
}
