package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Integer> {
    Airport findByAirportName(String name);
}
