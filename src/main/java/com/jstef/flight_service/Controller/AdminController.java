package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Service.FlightService;
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

    @GetMapping("/flights")
    public String adminFlightsList(Model model){
        List<Flight> flights = flightService.findAll();
        model.addAttribute("flights",flights);
        return "admin_flights_list";
    }
}
