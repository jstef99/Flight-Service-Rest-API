package com.jstef.flight_service.Resource;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api/flights")
public class FlightResource {

    @Autowired
    private FlightService flightService;

    @GET
    @Produces("application/json")
    public List<Flight> getFlights(){
        List<Flight> flights = flightService.findAll();
        return flights;
    }

    @GET
    @Produces("application/json")
    @Path("/{flight_id}")
    public Flight getFlightById(@PathParam("flight_id") int flightId){
        Flight flight = flightService.findById(flightId);
        return flight;
    }
}
