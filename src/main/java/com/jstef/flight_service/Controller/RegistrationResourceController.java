package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.RegistrationService;
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
@RequestMapping("/api/registrations")
public class RegistrationResourceController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public List<Registration> getRegistrations(){
        List<Registration> registrations = registrationService.findAll();
        return registrations;
    }

    @GetMapping(path = "/{registration_id}", produces = MediaType.APPLICATION_JSON)
    public Registration getRegistrationById(@PathVariable("registration_id") int registrationId){
        Registration registration = registrationService.findById(registrationId);
        return registration;
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public Registration addNewRegistration(@RequestBody Registration registration){
        if(registration==null) return new Registration();
        registration.setId(0);
        registrationService.save(registration);
        return registration;
    }

    @PutMapping(path = "/{registration_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Registration modifyRegistration(@PathVariable("registration_id") int registrationId, @RequestBody Registration registration) {
        Registration registrationFromDB = registrationService.findById(registrationId);
        if(registrationFromDB==null){
            registration.setId(0);
            registrationService.save(registration);
            return registration;
        }
        registrationFromDB.copyProperties(registration);
        registrationService.save(registrationFromDB);
        return registrationFromDB;
    }

    @DeleteMapping(path="/{registration_id}", produces = MediaType.APPLICATION_JSON)
    public String deleteRegistration(@PathVariable("registration_id") int registrationId){
        registrationService.deleteById(registrationId);
        return "success";
    }

}