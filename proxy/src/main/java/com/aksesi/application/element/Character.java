package com.aksesi.application.element;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class Character implements PasswordElement {

    private java.lang.Character character;

    public Character(java.lang.Character character) {
        this.character = character;
    }

    public java.lang.Character getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "CharacterElement{" +
                "character=" + character +
                '}';
    }
}
