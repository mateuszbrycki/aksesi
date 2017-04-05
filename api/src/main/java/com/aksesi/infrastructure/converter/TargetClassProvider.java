package com.aksesi.infrastructure.converter;

import com.aksesi.application.element.Character;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.element.PasswordElement;
import com.aksesi.infrastructure.dto.CharacterDTO;
import com.aksesi.infrastructure.dto.GestureDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by Mateusz Brycki on 04/04/2017.
 */
@Component
public class TargetClassProvider {

    private HashMap<Class<?>, Class<? extends PasswordElement>> classesMap = new HashMap<>();

    public Class<? extends PasswordElement> getTargetType(Class<?> clazz) {
        return classesMap.get(clazz);
    }

    public void register(Class<?> sourceClass, Class<? extends PasswordElement> elementClass) {
        classesMap.put(sourceClass, elementClass);
    }
}
