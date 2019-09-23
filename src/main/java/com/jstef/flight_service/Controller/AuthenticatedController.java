package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticatedController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/")
    public String homePage(){
        return "home_page";
    }
    @GetMapping("/profile")
    public String userProfile(){
        return "profile_page";
    }

    @GetMapping("/browse")
    public String browseFlights(Model model){
        model.addAttribute("flight",new Flight());
        return "flightBrowser_form";
    }

    @PostMapping("/browse")
    public String browseFlightsByCriteria(@ModelAttribute("flight") Flight flight, Model model){
        List<Flight>results=flightService.findByDeparturePlaceAndDestinationAndDepartureTime(flight.getDeparturePlace(),flight.getDestination(),flight.getDepartureTime());
        model.addAttribute("results",results);
        return "flightBrowser_result";
    }

}
