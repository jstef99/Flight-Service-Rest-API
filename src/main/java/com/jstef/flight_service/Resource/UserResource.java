package com.jstef.flight_service.Resource;

import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    @Produces("application/json")
    public List<User> getUsers(){
        List<User> users = userService.findAll();
        return users;
    }

    @GET
    @Produces("application/json")
    @Path("/{user_id}")
    public User getRegistrationById(@PathParam("user_id") int userId){
        User user = userService.findById(userId);
        return user;
    }
}
