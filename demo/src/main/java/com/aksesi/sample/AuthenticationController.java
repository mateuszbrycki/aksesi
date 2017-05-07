package com.aksesi.sample;

import com.aksesi.sample.request.AuthenticationRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Brycki on 28/04/2017.
 */
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationController {

    private Map<String, String> users = new HashMap<>();

    public AuthenticationController() {
        users.put("admin", "a");
        users.put("mateusz", "LineDIAGONAL_RIGHT");
        users.put("mateusz2", "aLineDIAGONAL_RIGHT");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(AuthenticationRequest request) {

        String login = request.getLogin();
        String password = users.get(login);

        if(password != null) {
            if(password.equals(request.getPassword())) {
                return Response.ok().build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
