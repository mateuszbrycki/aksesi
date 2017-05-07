package com.aksesi.infrastructure.converter;

import com.aksesi.application.element.Character;
import com.aksesi.infrastructure.dto.CharacterDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class CharacterDTOConverter implements Converter<CharacterDTO, Character> {

    @Override
    public Character convert(CharacterDTO characterElementDTO) {
        return new Character(characterElementDTO.getCharacter());
    }
}
