package com.jstef.flight_service.Config;

import com.jstef.flight_service.Entity.Role;
import com.jstef.flight_service.Service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findByName("ROLE_USER")==null){
            roleService.save(new Role("ROLE_USER"));
        }
        if(roleService.findByName("ROLE_ADMIN")==null){
            roleService.save(new Role("ROLE_ADMIN"));
        }
    }
}
