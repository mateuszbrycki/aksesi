package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.converter.strategy.ai.modifier.CentricPointsModifierTests;
import com.aksesi.application.converter.strategy.ai.supplier.PointsToArraySupplierTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AIConversionStrategyTests.class,
        CentricPointsModifierTests.class,
        PointsToArraySupplierTests.class
})
public class AISuite {
}
