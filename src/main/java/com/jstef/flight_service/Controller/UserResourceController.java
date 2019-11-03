package com.jstef.flight_service.Controller;

import com.jstef.flight_service.Assembler.UserResourceAssembler;
import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/users")
public class UserResourceController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceAssembler assembler;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON)
    public Resources<Resource<User>> getUsers(){
        List<Resource<User>> users = userService.findAll().stream().map(assembler::toResource).collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserResourceController.class).getUsers()).withSelfRel());
    }

    @GetMapping(path = "/{user_id}", produces = MediaType.APPLICATION_JSON)
    public Resource<User> getUserById(@PathVariable("user_id") int userId){
        User user = userService.findById(userId);
        return assembler.toResource(user);
    }

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public ResponseEntity<?> addNewUser(@RequestBody User user) throws URISyntaxException {
        if(user==null){
            user = new User();
        }
        user.setId(0);
        userService.saveNewUser(user);
        Resource<User> userResource = assembler.toResource(user);
        return ResponseEntity.created(new URI(userResource.getId().expand().getHref())).body(userResource);
    }

    @PutMapping(path = "/{user_id}",consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> modifyUser(@PathVariable("user_id") int userId,@RequestBody User user) throws URISyntaxException {
        User userFromDB = userService.findById(userId);
        if(userFromDB==null){
            user.setId(userId);
            userService.saveNewUser(user);
            Resource<User> userResource = assembler.toResource(user);
            return ResponseEntity.created(new URI(userResource.getId().expand().getHref())).body(userResource);
        }
        userFromDB.copyProperties(user);
        userFromDB.setId(userId);
        userService.saveNewUser(userFromDB);
        Resource<User> userResource = assembler.toResource(userFromDB);
        return ResponseEntity.created(new URI(userResource.getId().expand().getHref())).body(userResource);
    }

    @DeleteMapping(path="/{user_id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") int userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
