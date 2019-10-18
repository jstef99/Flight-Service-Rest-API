package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.AirportService;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.RegistrationService;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/flights")
    public String adminFlightsList(Model model){
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights",flights);
        return "admin_flights_list";
    }

    @GetMapping("/airports")
    public String adminAirportsList(Model model){
        List<Airport> airports = airportService.findAll();
        model.addAttribute("airports",airports);
        return "admin_airports_list";
    }

    @GetMapping("/users")
    public String adminUsersList(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "admin_users_list";
    }

    @GetMapping("/registrations")
    public String adminRegistrationsList(Model model){
        List<Registration> registrations = registrationService.findAll();
        model.addAttribute("registrations",registrations);
        return "admin_registrations_list";
    }
}
