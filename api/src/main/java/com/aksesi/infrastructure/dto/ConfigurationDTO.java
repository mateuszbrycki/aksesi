package com.aksesi.infrastructure.dto;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 09/04/2017.
 */
public class ConfigurationDTO implements Serializable {

    private String url;
    private String method;
    private InputConfiguration inputConfiguration;

    public ConfigurationDTO() {
    }

    public ConfigurationDTO(InputConfiguration inputConfiguration, String url, String method) {
        this.inputConfiguration = inputConfiguration;
        this.url = url;
        this.method = method;
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
}
