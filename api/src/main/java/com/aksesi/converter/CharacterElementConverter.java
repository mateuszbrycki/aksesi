package com.aksesi.converter;

import com.aksesi.element.CharacterElement;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Component
public class CharacterElementConverter extends AbstractConverter<CharacterElement> {

    public CharacterElementConverter() {
        super(CharacterElement.class);
    }

    @Override
    public String convert(CharacterElement element) {
        return element.getCharacter().toString();
    }

}
