package com.aksesi.configuration;

import com.aksesi.infrastructure.annotation.Representation;
import com.aksesi.infrastructure.converter.TargetClassProvider;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Mateusz Brycki on 05/04/2017.
 */
@Component
public class TargetClassProviderPostProcessor implements BeanPostProcessor {

    private final String APPLICATION_INFRASTRUCTURE_PACKAGE = "com.aksesi.infrastructure";

    private final Class<Representation> annotationClass = Representation.class;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof TargetClassProvider) {
            TargetClassProvider targetClassProvider = (TargetClassProvider) bean;

            Set<Class<?>> typesAnnotatedWith = new Reflections(APPLICATION_INFRASTRUCTURE_PACKAGE).getTypesAnnotatedWith(annotationClass);
            typesAnnotatedWith
                    .forEach(bd -> {
                        Class clazz = null;
                        try {
                            clazz = Class.forName(bd.getName());
                        } catch (ClassNotFoundException e) {
                            return;
                        }
                        Representation representation = bd.getAnnotation(annotationClass);
                        targetClassProvider.register(clazz, representation.element());
                    });

        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
