package com.jstef.flight_service.Service;

import com.jstef.flight_service.Entity.Role;
import com.jstef.flight_service.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional
    public void save(Role newRole){
        roleRepository.save(newRole);
    }
}
