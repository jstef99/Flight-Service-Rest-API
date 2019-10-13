package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Helper.FlightValidator;
import com.jstef.flight_service.Helper.TicketTransaction;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.RegistrationService;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/info/{flight_id}")
    public String getFlightInfo(@PathVariable("flight_id") int flightId, Model model){
        model.addAttribute("flight",flightService.findById(flightId));
        return "flight_info";
    }

    @GetMapping("buy_ticket/{flight_id}")
    public String viewBuyTicketForm(@PathVariable("flight_id") int flightId, Model model){
        Flight flight = flightService.findById(flightId);
        if(flight==null) return "/";
        TicketTransaction ticketTransaction = new TicketTransaction(flightId,0,0);//flight.getNPrice(),flight.getDPrice());
        model.addAttribute("transaction",ticketTransaction);
        return "buy_ticket_form";
    }

    @PostMapping("confirm_registration")
    public String confirmRegistration(@ModelAttribute("transaction") TicketTransaction transaction, Model model, Principal principal){
        Flight flight = flightService.findById(transaction.getFlightId());
        User user = userService.findByUsername(principal.getName());
        if(new FlightValidator().validate(flight,transaction)){
            float total = transaction.computeTotal();
            Registration registration = new Registration(transaction.getAdults(),transaction.getChildren(),total,user,flight);
            registrationService.save(registration);
            flight.setCurrPassengers(flight.getCurrPassengers()+transaction.getChildren()+transaction.getAdults());
            flightService.save(flight);
            model.addAttribute("message",new String("You successfully registered for a flight. In order to finalize" +
                    " transaction, visit My registrations in profile"));
        }
        else{
            model.addAttribute("message","Unable to register.");
        }
        return "registration_message";
    }
}
