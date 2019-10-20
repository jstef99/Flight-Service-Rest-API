package com.jstef.flight_service.Service;

import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public void save(Registration registration) {
        registrationRepository.save(registration);
    }

    public Registration findById(int id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) {
        registrationRepository.deleteById(id);
    }

    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }
}
