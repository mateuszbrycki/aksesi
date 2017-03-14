package com.aksesi;

import com.aksesi.element.PasswordElement;

import java.util.Collection;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
public class Password {

    private Collection<PasswordElement> elements;

    public Password(Collection<PasswordElement> elements) {
        this.elements = elements;
    }

    public int length() {
        return elements.size();
    }

    public Collection<PasswordElement> getElements() {
        return elements;
    }

}
