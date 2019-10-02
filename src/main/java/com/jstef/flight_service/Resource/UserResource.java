package com.jstef.flight_service.Resource;

import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api/users")
@RestController
public class UserResource {
    @GET
    @Produces("application/json")
    public String getUsers(){
        return null;
    }
}
