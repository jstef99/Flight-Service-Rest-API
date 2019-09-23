package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration,Integer> {
}
