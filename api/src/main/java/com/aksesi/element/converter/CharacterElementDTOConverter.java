package com.aksesi.element.converter;

import com.aksesi.element.CharacterElement;
import com.aksesi.element.dto.CharacterElementDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class CharacterElementDTOConverter implements Converter<CharacterElementDTO, CharacterElement> {

    @Override
    public CharacterElement convert(CharacterElementDTO characterElementDTO) {
        return new CharacterElement(characterElementDTO.getCharacter());
    }
}
