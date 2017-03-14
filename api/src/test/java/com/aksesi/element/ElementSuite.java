package com.aksesi.element;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        CharacterElementTests.class,
        GestureElementTests.class,
        PointTests.class
})
public class ElementSuite {
}
