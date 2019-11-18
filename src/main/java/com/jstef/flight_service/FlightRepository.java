package com.jstef.flight_service;

import com.jstef.flight_service.Entity.Airport;
import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    @Query("select a from Flight a where a.departureTime >= :departureTime and a.departurePlace=:departurePlace and a.destination=:destination")
    List<Flight> findAllWithDepartureTimeAndDeparturePlaceAndDestination(@Param("departureTime") Date departureTime,
                                                                              @Param("departurePlace") Airport departurePlace,
                                                                              @Param("destination") Airport destination);

    @Query("select a from Flight a where a.departureTime >= :departureTime")
    List<Flight> findAllWithDepartureTime(@Param("departureTime") Date departureTime);
}
