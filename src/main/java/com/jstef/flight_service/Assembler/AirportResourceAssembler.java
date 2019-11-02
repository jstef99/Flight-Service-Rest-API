package com.jstef.flight_service.Assembler;

import com.jstef.flight_service.Controller.AirportResourceController;
import com.jstef.flight_service.Entity.Airport;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class AirportResourceAssembler implements ResourceAssembler<Airport, Resource<Airport>> {

    @Override
    public Resource<Airport> toResource(Airport airport) {
        return new Resource<>(airport,
                linkTo(methodOn(AirportResourceController.class).getAirportById(airport.getId())).withSelfRel(),
                linkTo(methodOn(AirportResourceController.class).getAirports()).withRel("airports"));
    }
}
