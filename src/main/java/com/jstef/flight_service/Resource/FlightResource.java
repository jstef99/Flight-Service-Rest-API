package com.jstef.flight_service.Resource;

import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/api/flights")
@RestController
public class FlightResource {
    @GET
    @Produces("application/json")
    public String getFlights(){
        return null;
    }
}
