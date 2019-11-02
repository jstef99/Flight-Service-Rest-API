package com.jstef.flight_service.Assembler;

import com.jstef.flight_service.Controller.AirportResourceController;
import com.jstef.flight_service.Controller.FlightResourceController;
import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class FlightResourceAssembler implements ResourceAssembler<Flight, Resource<Flight>> {

    @Override
    public Resource<Flight> toResource(Flight flight) {
        return new Resource<>(flight,
                linkTo(methodOn(FlightResourceController.class).getFlightById(flight.getId())).withSelfRel(),
                linkTo(methodOn(FlightResourceController.class).getFlights()).withRel("flights"));
    }
}
