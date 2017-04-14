package com.aksesi.infrastructure;

import com.aksesi.infrastructure.converter.TargetClassProviderTests;
import com.aksesi.infrastructure.response.MessageResponseTests;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/04/2017.
 */
@Suite.SuiteClasses({
    TargetClassProviderTests.class,
    MessageResponseTests.class
})
public class InfrastructureSuite {
}

