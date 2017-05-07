package com.aksesi.infrastructure.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 09/04/2017.
 */
public class ConfigurationDTO implements Serializable {

    private String url;
    private String method;
    private InputConfiguration inputConfiguration;

    private Map<String, String> headers;

    public ConfigurationDTO() {
    }

    public ConfigurationDTO(InputConfiguration inputConfiguration, String url, String method, Map<String, String> headers) {
        this.inputConfiguration = inputConfiguration;
        this.url = url;
        this.method = method;
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public InputConfiguration getInputConfiguration() {
        return inputConfiguration;
    }

    public void setInputConfiguration(InputConfiguration inputConfiguration) {
        this.inputConfiguration = inputConfiguration;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
