package com.jstef.flight_service.Config;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Role;
import com.jstef.flight_service.Service.AirportService;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.RoleService;
import net.bytebuddy.asm.Advice;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        if(airportService.findByAirportName("Kato")==null){
            airportService.save(new Airport("Poland","Kato"));
        }
        if(airportService.findByAirportName("War")==null){
            airportService.save(new Airport("Poland","War"));
        }
        Flight flight = flightService.findById(1);
        if(flightService.findById(1)==null) {
            flight = new Flight(airportService.findByAirportName("Kato"),airportService.findByAirportName("War"),
                    DateTime.parse("2019-01-01T08:00:00.618-00:00"),  DateTime.parse("2019-01-01T12:30:00.618-00:00"),100);
            flightService.save(flight);
        }
    }
}
