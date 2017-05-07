package com.aksesi.infrastructure.template;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Mateusz Brycki on 29/04/2017.
 */
public class AksesiRestTemplate extends RestTemplate {

    public AksesiRestTemplate() {}

    public AksesiRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

}
