package com.aksesi.application.converter;

import com.aksesi.application.converter.strategy.ai.AISuite;
import com.aksesi.application.converter.strategy.linear.LinearConversionStrategyTests;
import com.aksesi.application.converter.strategy.linear.LinearSuite;
import com.aksesi.application.shape.direction.LineDirectionResolverTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        CharacterConverterTests.class,
        GestureConverterTests.class,
        AISuite.class,
        LinearSuite.class
})
public class ConverterSuite {
}
