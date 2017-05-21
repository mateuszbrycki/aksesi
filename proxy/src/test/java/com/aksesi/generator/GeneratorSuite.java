package com.aksesi.generator;

import com.aksesi.generator.shape.*;
import com.aksesi.generator.shape.mutator.DefaultMutatorTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        LineHorizontalGeneratorTests.class,
        LineVerticalGeneratorTests.class,
        LineDiagonalRightGeneratorTests.class,
        LineDiagonalLeftGeneratorTests.class,
        CircleGeneratorTests.class,
        DefaultMutatorTests.class
})
public class GeneratorSuite {
}