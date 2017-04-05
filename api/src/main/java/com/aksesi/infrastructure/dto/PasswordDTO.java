package com.aksesi.infrastructure.dto;

import com.aksesi.application.element.Character;
import com.aksesi.application.element.Password;
import com.aksesi.infrastructure.annotation.Representation;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@Representation(element = Password.class)
public class PasswordDTO implements Serializable {

    private Collection<PasswordElementDTO> elements;

    public PasswordDTO() {}

    public PasswordDTO(Collection<PasswordElementDTO> elements) {
        this.elements = elements;
    }

    public void setElements(Collection<PasswordElementDTO> elements) {
        this.elements = elements;
    }

    public Collection<PasswordElementDTO> getElements() {
        return elements;
    }

}
