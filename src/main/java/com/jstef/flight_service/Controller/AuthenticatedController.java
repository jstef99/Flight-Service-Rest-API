package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.Role;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Helper.FlightBrowse;
import com.jstef.flight_service.Service.AirportService;
import com.jstef.flight_service.Service.FlightService;
import com.jstef.flight_service.Service.UserService;
import org.glassfish.hk2.api.messaging.Topic;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class AuthenticatedController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AirportService airportService;

    @GetMapping("/")
    public String homePage(){
        return "home_page";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user",user);
        return "profile_page";
    }

    @GetMapping("/user/change_password")
    public String changePassword(Principal principal, HttpServletRequest request,Model model){
        String name = principal.getName();
        User user = userService.findByUsername(name);
        request.getSession().setAttribute("roles",user.getRoles());
        request.getSession().setAttribute("reservations",user.getReservations());
        model.addAttribute("user",user);
        return "password_update_form";
    }
    @PostMapping("/user/change_password")
    public String changePasswordSubmit(@Valid @ModelAttribute("user")User user,BindingResult result,HttpServletRequest request){
        Collection<Role> roles = (Collection<Role>)request.getSession().getAttribute("roles");
        List<Registration> reservations = (List<Registration>)request.getSession().getAttribute("reservations");
        user.setRoles(roles);
        user.setReservations(reservations);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/user/change_email")
    public String updateUser(Principal principal, Model model, HttpServletRequest request){
        String name = principal.getName();
        User user = userService.findByUsername(name);
        request.getSession().setAttribute("roles",user.getRoles());
        request.getSession().setAttribute("reservations",user.getReservations());
        model.addAttribute("user",user);
        return "email_update_form";
    }

    @PostMapping("/user/change_email")
    public String updateUserSubmit(@Valid @ModelAttribute("user")User user, BindingResult result, HttpServletRequest request,
                                   Model model){
        Collection<Role> roles = (Collection<Role>)request.getSession().getAttribute("roles");
        List<Registration> reservations = (List<Registration>)request.getSession().getAttribute("reservations");
        user.setRoles(roles);
        user.setReservations(reservations);
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/user/registrations")
    public String listRegistrations(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("reservations",user.getReservations());
        return "registrations_list";
    }
}
