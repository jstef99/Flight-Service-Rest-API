package com.jstef.flight_service.Service;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import com.jstef.flight_service.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;


    public List<Flight> findAllWithDepartureTimeAfterAndDeparturePlaceAndDestination(Airport departurePlace, Airport destination, Date departureTime) {
        return flightRepository.findAllWithDepartureTimeAndDeparturePlaceAndDestination(departureTime, departurePlace, destination);
    }

    public Flight findById(int flightId) {
         return flightRepository.findById(flightId).orElse(null);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public void save(Flight flight){
        flightRepository.save(flight);
    }

    public void removeRegistration(Registration registration) {
        Flight flight = findById(registration.getFlight().getId());
        flight.setCurrPassengers(flight.getCurrPassengers()-registration.getAdults()-registration.getChildren());
        save(flight);
    }

    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }
}
