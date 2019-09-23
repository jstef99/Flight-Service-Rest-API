package com.jstef.flight_service.Config;

import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    //setting user as session attribute on success and returning home
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String userName = authentication.getName();
        User theUser = userService.findByUsername(userName);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", theUser);

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
    }
}
