package com.aksesi.element.dto;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class CharacterElementDTO implements PasswordElementDTO, Serializable {

    private static final long serialVersionUID = 1L;

    private String type = "character";

    private Character character;

    public CharacterElementDTO() {}

    public CharacterElementDTO(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
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
