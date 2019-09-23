package com.jstef.flight_service.Service;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Exception.FlightNotFoundException;
import com.jstef.flight_service.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findByDeparturePlaceAndDestinationAndDepartureTime(String departurePlace, String destination, String departureTime) {
        return flightRepository.findByDeparturePlaceAndDestinationAndDepartureTime(departurePlace, destination, departureTime);
    }

    public Flight findById(int flightId) {
         return flightRepository.findById(flightId).orElseThrow(()->new FlightNotFoundException("Cannot find flight with id="+flightId));
    }
}
