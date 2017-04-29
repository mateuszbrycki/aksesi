package com.aksesi.infrastructure.template;

import com.aksesi.infrastructure.response.AksesiResponseHandlerProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 29/04/2017.
 */
@Component
public class AksesiRestTemplateProvider {

    @Autowired
    private AksesiResponseHandlerProvider provider;

    private AksesiRestTemplate template;

    public AksesiRestTemplate getTemplate() {

        if(template == null) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

            template = new AksesiRestTemplate(requestFactory);
            template.setErrorHandler(provider.getHandler());
        }

        return template;
    }
}
