package com.jstef.flight_service.Assembler;

import com.jstef.flight_service.Controller.AirportResourceController;
import com.jstef.flight_service.Controller.RegistrationResourceController;
import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Registration;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RegistrationResourceAssembler implements ResourceAssembler<Registration, Resource<Registration>> {

    @Override
    public Resource<Registration> toResource(Registration registration) {
        return new Resource<>(registration,
                linkTo(methodOn(RegistrationResourceController.class).getRegistrationById(registration.getId())).withSelfRel(),
                linkTo(methodOn(RegistrationResourceController.class).getRegistrations()).withRel("registrations"));
    }
}