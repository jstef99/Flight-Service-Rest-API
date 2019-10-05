package com.jstef.flight_service.Resource;

import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api/registrations")
@RestController
public class RegistrationResource {
    @GET
    @Produces("application/json")
    public String getRegistrations(){
        return null;
    }
}
