package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightResourceController {

    @Autowired
    private FlightService flightService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public List<Flight> getFlights(){
        List<Flight> flights = flightService.findAll();
        return flights;
    }

    @GetMapping(path = "/{flight_id}", produces = MediaType.APPLICATION_JSON)
    public Flight getFlightById(@PathVariable("flight_id") int flightId){
        Flight flight = flightService.findById(flightId);
        return flight;
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Flight addNewFlight(@RequestBody Flight flight){
        if(flight==null) return new Flight();
        flight.setId(0);
        flightService.save(flight);
        return flight;
    }

    @PutMapping(path = "/{flight_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Flight modifyFlight(@PathVariable("flight_id") int flightId,@RequestBody Flight flight) {
        Flight flightFromDB = flightService.findById(flightId);
        if(flightFromDB==null){
            flight.setId(0);
            flightService.save(flight);
            return flight;
        }
        flightFromDB.copyProperties(flight);
        flightService.save(flightFromDB);
        return flightFromDB;
    }

    @DeleteMapping(path="/{flight_id}", produces = MediaType.APPLICATION_JSON)
    public String deleteFlight(@PathVariable("flight_id") int flightId){
        flightService.deleteById(flightId);
        return "success";
    }

}