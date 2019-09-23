package com.jstef.flight_service.Service;

import com.jstef.flight_service.Repository.RegistrationRepository;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
}
