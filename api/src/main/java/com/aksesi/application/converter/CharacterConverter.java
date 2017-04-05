package com.aksesi.application.converter;

import com.aksesi.application.element.Character;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Component
public class CharacterConverter extends AbstractConverter<Character> {

    public CharacterConverter() {
        super(Character.class);
    }

    @Override
    public String convert(Character element) {
        return element.getCharacter().toString();
    }

}
