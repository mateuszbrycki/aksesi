package com.aksesi.infrastructure.dto;

import com.aksesi.application.element.Character;
import com.aksesi.infrastructure.annotation.Representation;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Representation(element = Character.class)
public class CharacterDTO extends PasswordElementDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.Character character;

    private static final String ELEMENT_TYPE = "character";

    public CharacterDTO() {
        super(ELEMENT_TYPE);
    }
    public CharacterDTO(java.lang.Character character) {
        super(ELEMENT_TYPE);
        this.character = character;
    }

    public java.lang.Character getCharacter() {
        return character;
    }

    public void setCharacter(java.lang.Character character) {
        this.character = character;
    }


    @Override
    public String toString() {
        return "CharacterElement{" +
                "character=" + character +
                '}';
    }
}
