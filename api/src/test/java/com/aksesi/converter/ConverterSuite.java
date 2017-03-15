package com.aksesi.converter;

import com.aksesi.converter.resolver.LineDirectionResolverTests;
import com.aksesi.converter.strategy.LinearConversionStrategyTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        CharacterElementConverterTests.class,
        GestureElementConverterTests.class,
        LineDirectionResolverTests.class,
        LinearConversionStrategyTests.class
})
public class ConverterSuite {
}
