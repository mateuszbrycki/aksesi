package com.aksesi.infrastructure;

import com.aksesi.infrastructure.converter.TargetClassProviderTests;
import com.aksesi.infrastructure.provider.AuthenticationRequestProviderTests;
import com.aksesi.infrastructure.response.MessageResponseTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Mateusz Brycki on 14/04/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TargetClassProviderTests.class,
    MessageResponseTests.class,
    AuthenticationRequestProviderTests.class
})
public class InfrastructureSuite {
}

