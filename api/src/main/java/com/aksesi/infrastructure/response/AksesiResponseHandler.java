package com.aksesi.infrastructure.response;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Mateusz Brycki on 29/04/2017.
 */
public class AksesiResponseHandler extends DefaultResponseErrorHandler {

    static Logger log = Logger.getLogger(AksesiResponseHandler.class.getName());

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.info("Handled response with code " + response.getStatusCode());
    }
}
