package com.aksesi.application.element;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        CharacterTests.class,
        GestureTests.class,
        PointTests.class,
        PasswordTests.class
})
public class ElementSuite {
}
