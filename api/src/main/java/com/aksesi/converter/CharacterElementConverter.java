package com.aksesi.converter;

import com.aksesi.element.CharacterElement;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class CharacterElementConverter extends AbstractConverter<CharacterElement> {

    public CharacterElementConverter() {
        super(CharacterElement.class);
    }

    @Override
    public String convert(CharacterElement element) {
        return element.getCharacter().toString();
    }

}
