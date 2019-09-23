package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    List<Flight> findByDeparturePlaceAndDestinationAndDepartureTime(String departurePlace, String destination, String departureTime);
}
