package com.aksesi;

import com.aksesi.application.converter.ConverterSuite;
import com.aksesi.application.element.ElementSuite;
import com.aksesi.application.encrypter.PasswordEncrypterTests;
import com.aksesi.application.shape.LineTests;
import com.aksesi.infrastructure.converter.TargetClassProviderTests;
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
        PasswordEncrypterTests.class,
        TargetClassProviderTests.class,
        AksesiIntegrationTests.class,
        AksesiContextApplicationTests.class,
        AksesiContextApplicationTestParametrized.class
})
public class AksesiSuite {
}
