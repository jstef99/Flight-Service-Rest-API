package com.jstef.flight_service.Service;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public void save(Airport airport){
        airportRepository.save(airport);
    }

    public Airport findByAirportName(String name){
        return airportRepository.findByAirportName(name);
    }
}
