package com.aksesi.infrastructure.converter;


import com.aksesi.application.element.Character;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.element.Password;
import com.aksesi.application.element.PasswordElement;
import com.aksesi.infrastructure.dto.GestureDTO;
import com.aksesi.infrastructure.dto.PasswordDTO;
import com.aksesi.infrastructure.dto.PasswordElementDTO;
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
    private TargetClassProvider targetClassProvider;

    public PasswordDTOConverter(@Autowired @Lazy ConversionService conversionService,
                                @Autowired TargetClassProvider targetClassProvider) {
        this.conversionService = conversionService;
        this.targetClassProvider = targetClassProvider;
    }

    @Override
    public Password convert(PasswordDTO passwordDTO) {

        List<PasswordElement> elements = convertElements(passwordDTO.getElements());
        return new Password(elements);
    }

    private List<PasswordElement> convertElements(Collection<PasswordElementDTO> elements) {
        List<PasswordElement> result = new ArrayList<>();

        elements.forEach(e -> {
            Class<? extends PasswordElement> targetType = targetClassProvider.getTargetType(e.getClass());
            Object element = conversionService.convert(e, targetType);
                result.add((PasswordElement)element);

        });

        return result;
    }
}
