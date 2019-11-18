package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.FlightRepository;
import com.jstef.flight_service.UserRepository;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightJPATestClass {
    @Autowired
    private TestEntityManager manager;

    @Autowired
    private FlightRepository flightRepository;

    @Test
    public void findByIdTest(){
        Flight flight = new Flight(0,5,100);
        manager.persist(flight);
        manager.flush();
        Flight desired = flightRepository.findById(flight.getId()).orElse(null);
        assertEquals(desired.getId(),flight.getId());
        assertEquals(desired.getMaxPassengers(),flight.getMaxPassengers());
        assertEquals(desired.getCurrPassengers(),flight.getCurrPassengers());
    }

    @Test
    public void findByDateTest(){
        Flight flight = new Flight(DateTime.parse("2019-01-01T08:00:00.618-00:00"));
        manager.persist(flight);
        manager.flush();
        Date date = DateTime.parse("2019-01-01T08:00:00.618-00:00").toDate();
        List<Flight> desired = flightRepository.findAllWithDepartureTime(date);
        assertEquals(desired.size(),1);
        assertTrue(desired.get(0).getDepartureTime().compareTo(date)==0);
    }

    @Test
    public void findByDateNoResultTest(){
        Flight flight = new Flight(DateTime.parse("2019-01-02T08:00:00.618-00:00"));
        manager.persist(flight);
        manager.flush();
        Date date = DateTime.parse("2019-01-03T08:00:00.618-00:00").toDate();
        List<Flight> desired = flightRepository.findAllWithDepartureTime(date);
        assertEquals(desired.size(),0);
    }
}
