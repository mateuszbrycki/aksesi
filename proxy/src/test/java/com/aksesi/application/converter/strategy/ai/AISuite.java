package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.converter.strategy.ai.aligner.CentricPointsAlignerTests;
import com.aksesi.application.converter.strategy.ai.resizer.RepeatingResizerTests;
import com.aksesi.application.converter.strategy.ai.supplier.FlattenPointsSupplierTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AIConversionStrategyTests.class,
        CentricPointsAlignerTests.class,
        FlattenPointsSupplierTests.class,
        RepeatingResizerTests.class
})
public class AISuite {
}
