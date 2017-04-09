package com.aksesi.configuration;

import com.aksesi.infrastructure.annotation.Representation;
import com.aksesi.infrastructure.converter.TargetClassProvider;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;

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

            //get all of classes annotated with @Representation
            Set<Class<?>> typesAnnotatedWith = new Reflections(APPLICATION_INFRASTRUCTURE_PACKAGE).getTypesAnnotatedWith(annotationClass);

            Consumer<Class<?>> register = new ClassRegistrationLogic(targetClassProvider);

            //register each of classes
            typesAnnotatedWith.forEach(register::accept);
        }

        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private class ClassRegistrationLogic implements Consumer<Class<?>> {

        TargetClassProvider provider;
        public ClassRegistrationLogic(TargetClassProvider provider) {
            this.provider = provider;
        }

        @Override public void accept(Class<?> aClass)
        {
            Class clazz;
            try {
                clazz = Class.forName(aClass.getName());
            } catch (ClassNotFoundException e) {
                return;
            }

            Representation representation = aClass.getAnnotation(annotationClass);
            provider.register(clazz, representation.element());
        }
    }
}
