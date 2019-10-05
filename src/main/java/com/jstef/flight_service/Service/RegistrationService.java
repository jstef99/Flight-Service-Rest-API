package com.jstef.flight_service.Service;

import com.jstef.flight_service.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
}
