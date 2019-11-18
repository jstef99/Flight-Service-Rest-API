package com.jstef.flight_service;

import com.jstef.flight_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String userName);
}
