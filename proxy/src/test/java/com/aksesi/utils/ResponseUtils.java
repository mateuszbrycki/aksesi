package com.aksesi.utils;

/**
 * Created by Mateusz Brycki on 09/04/2017.
 */
public class ResponseUtils {

    public static String prepareResponse(String responseMessage) {
        return "{\"message\": \"" + responseMessage + "\"}";
    }

}
