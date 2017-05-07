package com.aksesi.sample.request;

/**
 * Created by Mateusz Brycki on 28/04/2017.
 */
public class AuthenticationRequest {

    private String login;
    private String password;

    public AuthenticationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthenticationRequest() {

    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
