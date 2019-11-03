package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Assembler.RegistrationResourceAssembler;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.RegistrationService;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationResourceController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationResourceAssembler assembler;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public Resources<Resource<Registration>> getRegistrations(){
        List<Resource<Registration>> registrations = registrationService.findAll().stream().map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(registrations, linkTo(methodOn(RegistrationResourceController.class)
                .getRegistrations()).withSelfRel());
    }

    @GetMapping(path = "/{registration_id}", produces = MediaType.APPLICATION_JSON)
    public Resource<Registration> getRegistrationById(@PathVariable("registration_id") int registrationId){
        Registration registration = registrationService.findById(registrationId);
        return assembler.toResource(registration);
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addNewRegistration(@RequestBody Registration registration) throws URISyntaxException {
        if(registration==null) registration = new Registration();
        registration.setId(0);
        registrationService.save(registration);
        Resource<Registration> registrationResource = assembler.toResource(registration);
        return ResponseEntity.created(new URI(registrationResource.getId().expand().getHref())).body(registrationResource);
    }

    @PutMapping(path = "/{registration_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> modifyRegistration(@PathVariable("registration_id") int registrationId, @RequestBody Registration registration) throws URISyntaxException {
        Registration registrationFromDB = registrationService.findById(registrationId);
        if(registrationFromDB==null){
            registration.setId(registrationId);
            registrationService.save(registration);
            Resource<Registration> registrationResource = assembler.toResource(registration);
            return ResponseEntity.created(new URI(registrationResource.getId().expand().getHref())).body(registrationResource);
        }
        registrationFromDB.copyProperties(registration);
        registrationFromDB.setId(registrationId);
        registrationService.save(registrationFromDB);
        Resource<Registration> registrationResource = assembler.toResource(registration);
        return ResponseEntity.created(new URI(registrationResource.getId().expand().getHref())).body(registrationResource);
    }

    @DeleteMapping(path="/{registration_id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> deleteRegistration(@PathVariable("registration_id") int registrationId){
        registrationService.deleteById(registrationId);
        return ResponseEntity.noContent().build();
    }

}