package com.aksesi.configuration;

import com.aksesi.application.converter.AbstractConverter;
import com.aksesi.application.encrypter.PasswordEncrypter;
import com.aksesi.infrastructure.logger.AksesiLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mateusz Brycki on 04/04/2017.
 */
@Component
public class PasswordEncrypterPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final AksesiLogger logger = AksesiLogger.getLogger(PasswordEncrypterPostProcessor.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof PasswordEncrypter) {

            //find all of AbstractConverter implementations
            Map<String, AbstractConverter> beansOfType = applicationContext.getBeansOfType(AbstractConverter.class);

            //register all of found converters
            Set<AbstractConverter> converters = new HashSet(beansOfType.values());

            //cast bean
            PasswordEncrypter conversionFactoryBean = (PasswordEncrypter) bean;
            converters.forEach(conversionFactoryBean::registerConverter);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
