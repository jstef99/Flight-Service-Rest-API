package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserResourceController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public List<User> getUsers(){
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping(path = "/{user_id}", produces = MediaType.APPLICATION_JSON)
    public User getRegistrationById(@PathVariable("user_id") int userId){
        User user = userService.findById(userId);
        return user;
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public User addNewUser(@RequestBody User user){
        if(user==null) return new User();
        user.setId(0);
        userService.saveNewUser(user);
        return user;
    }

    @PutMapping(path = "/{user_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public User modifyUser(@PathVariable("user_id") int userId,@RequestBody User user) {
        User userFromDB = userService.findById(userId);
        if(userFromDB==null){
            user.setId(0);
            userService.saveNewUser(user);
            return user;
        }
        userFromDB.copyProperties(user);
        userService.saveNewUser(userFromDB);
        return userFromDB;
    }

    @DeleteMapping(path="/{user_id}", produces = MediaType.APPLICATION_JSON)
    public String deleteUser(@PathVariable("user_id") int userId){
        userService.deleteById(userId);
        return "success";
    }

}
