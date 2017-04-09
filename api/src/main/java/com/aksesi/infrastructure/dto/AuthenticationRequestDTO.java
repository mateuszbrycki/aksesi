package com.aksesi.infrastructure.dto;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 09/04/2017.
 */
public class AuthenticationRequestDTO implements Serializable {

    private String login;
    private PasswordDTO password;
    private ConfigurationDTO configuration;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(String login, PasswordDTO passwordDTO, ConfigurationDTO configuration) {
        this.login = login;
        this.password = passwordDTO;
        this.configuration = configuration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PasswordDTO getPassword() {
        return password;
    }

    public void setPasswordDTO(PasswordDTO passwordDTO) {
        this.password = passwordDTO;
    }

    public ConfigurationDTO getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ConfigurationDTO configuration) {
        this.configuration = configuration;
    }
}
