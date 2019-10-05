package com.jstef.flight_service.Config;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Role;
import com.jstef.flight_service.Service.AirportService;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findByName("ROLE_USER")==null){
            roleService.save(new Role("ROLE_USER"));
        }
        if(roleService.findByName("ROLE_ADMIN")==null){
            roleService.save(new Role("ROLE_ADMIN"));
        }
        if(airportService.findByAirportName("War")==null){
            airportService.save(new Airport("Poland","War"));
        }
        if(flightService.findAllWithDepartureTimeAfterAndDeparturePlaceAndDestination
                (new Airport("Poland","Kato"),new Airport("Poland","Wwwa")
                        ,new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01"))==null){
            flightService.save(new Flight());
        }
    }
}
