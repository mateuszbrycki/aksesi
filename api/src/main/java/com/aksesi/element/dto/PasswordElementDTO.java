package com.aksesi.element.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CharacterElementDTO.class, name = "character"),
        @JsonSubTypes.Type(value = GestureElementDTO.class, name = "gesture")
})
public interface PasswordElementDTO {
}
