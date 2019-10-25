package com.jstef.flight_service.Resource;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import java.util.List;

@Path("/api/airports")
public class AirportResource {
    @Autowired
    private AirportService airportService;

    @GET
    @Produces("application/json")
    public List<Airport> getAirports(){
        List<Airport> airports = airportService.findAll();
        return airports;
    }

    @GET
    @Produces("application/json")
    @Path("/{airport_id}")
    public Airport getAirportById(@PathParam("airport_id") int airportId){
        Airport airport = airportService.findById(airportId);
        return airport;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addNewAirport(Airport airport){
        airportService.save(airport);
        return "success";
    }
}
