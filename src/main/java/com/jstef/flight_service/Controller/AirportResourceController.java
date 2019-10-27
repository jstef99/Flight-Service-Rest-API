package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.AirportService;
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
@RequestMapping("/api/airports")
public class AirportResourceController {

    @Autowired
    private AirportService airportService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public List<Airport> getAirports(){
        List<Airport> airports = airportService.findAll();
        return airports;
    }

    @GetMapping(path = "/{airport_id}", produces = MediaType.APPLICATION_JSON)
    public Airport getAirportById(@PathVariable("airport_id") int airportId){
        Airport airport = airportService.findById(airportId);
        return airport;
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Airport addNewAirport(@RequestBody Airport airport){
        if(airport==null) return new Airport();
        airport.setId(0);
        airportService.save(airport);
        return airport;
    }

    @PutMapping(path = "/{airport_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Airport modifyAirport(@PathVariable("airport_id") int airportId, @RequestBody Airport airport) {
        Airport airportFromDB = airportService.findById(airportId);
        if(airportFromDB==null){
            airport.setId(0);
            airportService.save(airport);
            return airport;
        }
        airportFromDB.copyProperties(airport);
        airportService.save(airportFromDB);
        return airportFromDB;
    }

    @DeleteMapping(path="/{airport_id}", produces = MediaType.APPLICATION_JSON)
    public String deleteAirport(@PathVariable("airport_id") int airportId){
        airportService.deleteById(airportId);
        return "success";
    }

}