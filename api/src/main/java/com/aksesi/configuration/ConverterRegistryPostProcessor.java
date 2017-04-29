package com.aksesi.configuration;

import com.aksesi.infrastructure.logger.AksesiLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mateusz Brycki on 29/03/2017.
 */
@Component
public class ConverterRegistryPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private final String CONVERSION_SERVICE_NAME = "mvcConversionService";

    private ApplicationContext appCtx;

    private static final AksesiLogger logger = AksesiLogger.getLogger(ConverterRegistryPostProcessor.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (CONVERSION_SERVICE_NAME.equals(beanName)) {
            logger.info("ConversionService bean detected");

            Map<String, Converter> beansOfType = appCtx.getBeansOfType(Converter.class);
            DefaultFormattingConversionService conversionFactoryBean = (DefaultFormattingConversionService) bean;
            Set converters = new HashSet(beansOfType.values());

            converters.forEach(c -> conversionFactoryBean.addConverter((Converter)c));

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

}
