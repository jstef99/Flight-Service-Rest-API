package com.jstef.flight_service.Resource;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api/registrations")
public class RegistrationResource {

    @Autowired
    private RegistrationService registrationService;

    @GET
    @Produces("application/json")
    public List<Registration> getRegistrations(){
        List<Registration> registrations = registrationService.findAll();
        return registrations;
    }

    @GET
    @Produces("application/json")
    @Path("/{registration_id}")
    public Registration getRegistrationById(@PathParam("registration_id") int registrationId){
        Registration registration = registrationService.findById(registrationId);
        return registration;
    }
}
