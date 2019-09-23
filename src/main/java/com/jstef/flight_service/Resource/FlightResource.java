package com.jstef.flight_service.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/flights")
public class FlightResource {
    @GET
    @Produces("application/json")
    public String getFlights(){
        return null;
    }
}
