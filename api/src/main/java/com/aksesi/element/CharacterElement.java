package com.aksesi.element;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class CharacterElement implements PasswordElement {

    private Character character;

    public CharacterElement(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "CharacterElement{" +
                "character=" + character +
                '}';
    }
}
