package com.aksesi.infrastructure.dto;

import com.aksesi.application.element.Character;
import com.aksesi.infrastructure.annotation.Representation;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Representation(element = Character.class)
public class CharacterDTO implements PasswordElementDTO, Serializable {

    private static final long serialVersionUID = 1L;

    private String type = "character";

    private java.lang.Character character;

    public CharacterDTO() {}

    public CharacterDTO(java.lang.Character character) {
        this.character = character;
    }

    public java.lang.Character getCharacter() {
        return character;
    }

    public void setCharacter(java.lang.Character character) {
        this.character = character;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CharacterElement{" +
                "character=" + character +
                '}';
    }
}
