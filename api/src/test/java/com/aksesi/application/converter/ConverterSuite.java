package com.aksesi.application.converter;

import com.aksesi.application.shape.direction.LineDirectionResolverTests;
import com.aksesi.application.converter.strategy.LinearConversionStrategyTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        CharacterConverterTests.class,
        GestureConverterTests.class,
        LineDirectionResolverTests.class,
        LinearConversionStrategyTests.class
})
public class ConverterSuite {
}
