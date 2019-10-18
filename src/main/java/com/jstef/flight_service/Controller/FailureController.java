package com.jstef.flight_service.Controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FailureController implements ErrorController {
    @RequestMapping("/access_denied")
    public String deny(){
        return "access_denied";
    }
    @RequestMapping("/error")
    public String error(){
        return "error_page";
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
