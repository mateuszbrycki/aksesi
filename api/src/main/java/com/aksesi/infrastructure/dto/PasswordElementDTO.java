package com.aksesi.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CharacterDTO.class, name = "character"),
        @JsonSubTypes.Type(value = GestureDTO.class, name = "gesture")
})
public interface PasswordElementDTO {
}
