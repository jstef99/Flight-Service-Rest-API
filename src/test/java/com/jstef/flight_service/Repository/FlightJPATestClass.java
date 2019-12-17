package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.User;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightJPATestClass {
    @Autowired
    private TestEntityManager manager;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void shouldReturnValidFlightForFindById(){
        Flight flight = new Flight(0,5,100);
        manager.persist(flight);
        manager.flush();
        Flight desired = flightRepository.findById(flight.getId()).orElse(null);
        assertEquals(flight,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidFlightId(){
        Flight desired = flightRepository.findById(-1).orElse(null);
        assertNull(desired);
    }

    @Test
    public void shouldReturnValidFlightForFindByDate(){
        Flight flight = new Flight(DateTime.parse("2019-01-01T08:00:00.618-00:00"));
        manager.persist(flight);
        manager.flush();
        Date date = DateTime.parse("2019-01-01T08:00:00.618-00:00").toDate();
        List<Flight> desired = flightRepository.findAllWithDepartureTime(date);
        assertEquals(desired.size(),1);
        assertEquals(desired.get(0),flight);
    }

    @Test
    public void shouldReturnNoResultForFindByDate(){
        Flight flight = new Flight(DateTime.parse("2019-01-02T08:00:00.618-00:00"));
        manager.persist(flight);
        manager.flush();
        Date date = DateTime.parse("2019-01-03T08:00:00.618-00:00").toDate();
        List<Flight> desired = flightRepository.findAllWithDepartureTime(date);
        assertEquals(desired.size(),0);
    }
}
