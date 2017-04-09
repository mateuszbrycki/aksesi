package com.aksesi.infrastructure.response;

/**
 * Created by Mateusz Brycki on 09/04/2017.
 */
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
