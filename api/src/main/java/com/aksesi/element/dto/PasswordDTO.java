package com.aksesi.element.dto;

import com.aksesi.element.dto.PasswordElementDTO;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */

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
