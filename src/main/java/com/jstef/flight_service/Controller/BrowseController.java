package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Helper.FlightBrowse;
import com.jstef.flight_service.Service.AirportService;
import com.jstef.flight_service.Service.FlightService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/browse")
public class BrowseController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @GetMapping("/flights")
    public String browseFlights(Model model){
        model.addAttribute("flight",new FlightBrowse());
        return "flightBrowser_form";
    }

    @PostMapping("/flights")
    public String browseFlightsByCriteria(@ModelAttribute("flight") FlightBrowse flightBrowseObject, Model model) throws ParseException {
        if (flightBrowseObject == null) {
            return "redirect:/home";
        }
        try {
            List<Flight> results = flightService.findAllWithDepartureTimeAfterAndDeparturePlaceAndDestination(
                    airportService.findByAirportName(flightBrowseObject.getDeparturePlace()),
                    airportService.findByAirportName(flightBrowseObject.getDestination()),
                    DateTime.parse(flightBrowseObject.getDepartureTime()).toDate());
            model.addAttribute("results", results);
            return "flightBrowser_result";
        } catch (Exception e) {
            return "flightBrowser_form";
        }
    }

    @GetMapping("/airports")
    public String browseAirports(Model model){
        List<Airport>airports = airportService.findAll();
        model.addAttribute("airports",airports);
        return "airportBrowser_result";
    }


}
