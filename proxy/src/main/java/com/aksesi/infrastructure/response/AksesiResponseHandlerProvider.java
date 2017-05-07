package com.aksesi.infrastructure.response;

import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 29/04/2017.
 */
@Component
public class AksesiResponseHandlerProvider {

    public AksesiResponseHandler getHandler() {
        return new AksesiResponseHandler();
    }
}
