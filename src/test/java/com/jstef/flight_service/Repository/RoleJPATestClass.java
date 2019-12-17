package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleJPATestClass {
    @Autowired
    private TestEntityManager manager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldReturnRoleForFindByName(){
        Role role = new Role("default");
        manager.persist(role);
        manager.flush();
        Role desired = roleRepository.findByName("default");
        assertEquals(role,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidName(){
        Role role = roleRepository.findByName("not-a-role");
        assertNull(role);
    }
}
