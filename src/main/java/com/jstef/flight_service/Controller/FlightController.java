package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping("/flight_info/{flight_id}")
    public String getFlightInfo(@PathVariable("flight_id") int flightId, Model model){
        model.addAttribute("flight",flightService.findById(flightId));
        return "flight_info";
    }
}
