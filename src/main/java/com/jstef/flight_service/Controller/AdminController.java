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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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

    //admin lists of entities

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

    //non-api entities management interface

    @GetMapping("/registrations/modify/{registration_id}")
    public String modifyRegistration(Model model, @PathVariable("registration_id") int id){
        Registration registration = registrationService.findById(id);
        if(registration==null) return "/admin/registrations";
        model.addAttribute("registration", registration);
        return "registration_update_form";
    }

    @PostMapping("/registrations/modify")
    public String submitModifiedRegistration(@ModelAttribute("registration") Registration registration){
        Registration fromDB = registrationService.findById(registration.getId());
        fromDB.setAdults(registration.getAdults());
        fromDB.setChildren(registration.getChildren());
        fromDB.setTotal(registration.getTotal());
        registrationService.save(fromDB);
        return "redirect:/admin/registrations";
    }

    @GetMapping("/registrations/delete/{registration_id}")
    public String deleteRegistration(@PathVariable("registration_id") int id){
        registrationService.deleteById(id);
        return "redirect:/admin/registrations";
    }

    @GetMapping("/airports/modify/{airport_id}")
    public String modifyAirport(Model model, @PathVariable("airport_id") int id){
        Airport airport = airportService.findById(id);
        if(airport==null) return "/admin/airports";
        model.addAttribute("airport", airport);
        return "airport_update_form";
    }

    @PostMapping("/airports/modify")
    public String submitModifiedAirport(@ModelAttribute("airport") Airport airport){
        Airport fromDB = airportService.findById(airport.getId());
        fromDB.setCountry(airport.getCountry());
        fromDB.setAirportName(airport.getAirportName());
        airportService.save(fromDB);
        return "redirect:/admin/airports";
    }

    @GetMapping("/airports/delete/{airport_id}")
    public String deleteAirport(@PathVariable("airport_id") int id){
        airportService.deleteById(id);
        return "redirect:/admin/airports";
    }

    @GetMapping("/airports/add")
    public String addAirport(Model model){
        //stub
        Airport airport = new Airport();
        model.addAttribute("newAirport",airport);
        return "new_airport_form";
    }

    @PostMapping("/airports/add")
    public String addAirportConfirm(@ModelAttribute("newAirport") Airport airport){
        //stub
        return "redirect:/admin/airports";
    }

    @GetMapping("/flights/modify/{flight_id}")
    public String modifyFlight(Model model,@PathVariable("flight_id") int id){
        Flight flight = flightService.findById(id);
        if(flight==null) return "/admin/flights";
        model.addAttribute("flight", flight);
        return "flight_update_form";
    }

    @PostMapping("/flights/modify")
    public String submitModifiedFlight(@ModelAttribute("flight") Flight flight){
        Flight fromDB = flightService.findById(flight.getId());
        fromDB.setCurrPassengers(flight.getCurrPassengers());
        fromDB.setMaxPassengers(flight.getMaxPassengers());
        fromDB.setDPrice(flight.getDPrice());
        fromDB.setNPrice(flight.getNPrice());
        flightService.save(fromDB);
        return "redirect:/admin/flights";
    }

    @GetMapping("/flights/delete/{flight_id}")
    public String deleteFlight(@PathVariable("flight_id") int id){
        flightService.deleteById(id);
        return "redirect:/admin/flights";
    }

    @GetMapping("/users/modify/{user_id}")
    public String modifyUser(Model model, @PathVariable("user_id") int id){
        User user = userService.findById(id);
        if(user==null) return "/admin/users";
        model.addAttribute("user", user);
        return "user_update_form";
    }

    @PostMapping("/users/modify")
    public String submitModifiedUser(@ModelAttribute("user") User user){
        User fromDB = userService.findById(user.getId());
        fromDB.setEmail(user.getEmail());
        fromDB.setFirstName(user.getFirstName());
        fromDB.setLastName(user.getLastName());
        fromDB.setLogin(user.getLogin());
        userService.updateUser(fromDB);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{user_id}")
    public String deleteUser(@PathVariable("user_id") int id){
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/generate_api/{user_id}")
    public String generateKey(@PathVariable("user_id") int id){
        String key = UUID.randomUUID().toString();
        User user = userService.findById(id);
        user.setApiKey(key);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

}
