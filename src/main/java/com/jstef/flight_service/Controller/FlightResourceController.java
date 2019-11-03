package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Assembler.FlightResourceAssembler;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/flights")
public class FlightResourceController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightResourceAssembler assembler;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public Resources<Resource<Flight>> getFlights(){
        List<Resource<Flight>> flights = flightService.findAll().stream().map(assembler::toResource).collect(Collectors.toList());
        return new Resources<>(flights,linkTo(methodOn(FlightResourceController.class).getFlights()).withSelfRel());
    }

    @GetMapping(path = "/{flight_id}", produces = MediaType.APPLICATION_JSON)
    public Resource<Flight> getFlightById(@PathVariable("flight_id") int flightId){
        Flight flight = flightService.findById(flightId);
        return assembler.toResource(flight);
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addNewFlight(@RequestBody Flight flight) throws URISyntaxException {
        if(flight==null) flight = new Flight();
        flight.setId(0);
        flightService.save(flight);
        Resource<Flight> flightResource = assembler.toResource(flight);
        return ResponseEntity.created(new URI(flightResource.getId().expand().getHref())).body(flightResource);
    }

    @PutMapping(path = "/{flight_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> modifyFlight(@PathVariable("flight_id") int flightId, @RequestBody Flight flight) throws URISyntaxException {
        Flight flightFromDB = flightService.findById(flightId);
        if(flightFromDB==null){
            flight.setId(flightId);
            flightService.save(flight);
            Resource<Flight> flightResource = assembler.toResource(flight);
            return ResponseEntity.created(new URI(flightResource.getId().expand().getHref())).body(flightResource);
        }
        flightFromDB.copyProperties(flight);
        flightFromDB.setId(flightId);
        flightService.save(flightFromDB);
        Resource<Flight> flightResource = assembler.toResource(flightFromDB);
        return ResponseEntity.created(new URI(flightResource.getId().expand().getHref())).body(flightResource);
    }

    @DeleteMapping(path="/{flight_id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> deleteFlight(@PathVariable("flight_id") int flightId){
        flightService.deleteById(flightId);
        return ResponseEntity.noContent().build();
    }

}