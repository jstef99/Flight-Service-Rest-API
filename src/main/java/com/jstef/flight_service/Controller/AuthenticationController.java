package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String loginPage(){
        return "login_page";
    }
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        User newUser = new User();
        model.addAttribute("user",newUser);
        return "register_form";
    }

    @PostMapping("/register/submit")
    public String submitUser(@ModelAttribute("user") User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "register_form";
        }
        String username = user.getLogin();
        User existing = service.findByUsername(username);
        if (existing != null){
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "User with this username already exists.");
            return "register_form";
        }
        service.saveNewUser(user);
        return "redirect:/login";
    }
}
