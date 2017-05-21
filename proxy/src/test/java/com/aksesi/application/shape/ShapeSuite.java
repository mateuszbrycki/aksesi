package com.aksesi.application.shape;

import com.aksesi.application.shape.direction.LineDirectionResolverTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        LineDirectionResolverTests.class,
        LineTests.class
})
public class ShapeSuite {
}