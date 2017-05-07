package com.aksesi.infrastructure.dto;

import java.io.Serializable;

/**
 * Created by Mateusz Brycki on 23/04/2017.
 */
public class InputConfiguration implements Serializable {
    private String loginName;
    private String passwordName;

    public InputConfiguration() {}

    public InputConfiguration(String loginName, String passwordName) {
        this.loginName = loginName;
        this.passwordName = passwordName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPasswordName() {
        return passwordName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPasswordName(String passwordName) {
        this.passwordName = passwordName;
    }

    @Override
    public String toString() {
        return "InputConfiguration{" +
                "loginName='" + loginName + '\'' +
                ", passwordName='" + passwordName + '\'' +
                '}';
    }
}
