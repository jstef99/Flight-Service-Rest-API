package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Assembler.AirportResourceAssembler;
import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.AirportService;
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
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("/api/airports")
public class AirportResourceController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private AirportResourceAssembler assembler;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public Resources<Resource<Airport>> getAirports(){
        List<Resource<Airport>> airports = airportService.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(airports,
                linkTo(methodOn(AirportResourceController.class).getAirports()).withSelfRel());
    }

    @GetMapping(path = "/{airport_id}", produces = MediaType.APPLICATION_JSON)
    public Resource<Airport> getAirportById(@PathVariable("airport_id") int airportId){
        Airport airport = airportService.findById(airportId);
        return assembler.toResource(airport);
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addNewAirport(@RequestBody Airport airport) throws URISyntaxException {
        airport.setId(0);
        airportService.save(airport);
        Resource<Airport> airportResource = assembler.toResource(airport);
        return ResponseEntity.created(new URI(airportResource.getId().expand().getHref())).body(airportResource);
    }

    @PutMapping(path = "/{airport_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> modifyAirport(@PathVariable("airport_id") int airportId, @RequestBody Airport airport) throws URISyntaxException {
        Airport airportFromDB = airportService.findById(airportId);
        if(airportFromDB==null){
            airport.setId(airportId);
            airportService.save(airport);
            Resource<Airport>airportResource=assembler.toResource(airport);
            return ResponseEntity.created(new URI(airportResource.getId().expand().getHref())).body(airportResource);
        }
        airportFromDB.copyProperties(airport);
        airportFromDB.setId(airportId);
        airportService.save(airportFromDB);
        Resource<Airport>airportResource=assembler.toResource(airportFromDB);
        return ResponseEntity.created(new URI(airportResource.getId().expand().getHref())).body(airportResource);
    }

    @DeleteMapping(path="/{airport_id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> deleteAirport(@PathVariable("airport_id") int airportId){
        airportService.deleteById(airportId);
        return ResponseEntity.noContent().build();
    }
}