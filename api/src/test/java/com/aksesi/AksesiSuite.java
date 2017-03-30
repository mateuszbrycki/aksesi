package com.aksesi;

import com.aksesi.converter.ConverterSuite;
import com.aksesi.element.ElementSuite;
import com.aksesi.element.PasswordTests;
import com.aksesi.service.PasswordConversionServiceTests;
import com.aksesi.shape.LineTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        ElementSuite.class,
        ConverterSuite.class,
        LineTests.class,
        PasswordConversionServiceTests.class,
        AksesiIntegrationTests.class,
        AksesiApplicationTests.class
})
public class AksesiSuite {
}
