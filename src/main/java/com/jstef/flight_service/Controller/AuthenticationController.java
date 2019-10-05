package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @PostMapping("/logout")
    public String logoutDo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        session= request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "redirect:/login";
    }
}
