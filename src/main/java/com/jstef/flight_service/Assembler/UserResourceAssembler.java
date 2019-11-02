package com.jstef.flight_service.Assembler;

import com.jstef.flight_service.Controller.AirportResourceController;
import com.jstef.flight_service.Controller.UserResourceController;
import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    @Override
    public Resource<User> toResource(User user) {
        return new Resource<>(user,
                linkTo(methodOn(UserResourceController.class).getUserById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserResourceController.class).getUsers()).withRel("users"));
    }
}