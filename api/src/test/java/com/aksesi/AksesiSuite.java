package com.aksesi;

import com.aksesi.element.ElementSuite;
import com.aksesi.password.PasswordTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        ElementSuite.class,
        PasswordTests.class
})
public class AksesiSuite {
}
