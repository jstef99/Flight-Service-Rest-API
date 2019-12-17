package com.jstef.flight_service.Repository;

import com.jstef.flight_service.Entity.Airport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AirportJPATestClass {
    @Autowired
    private TestEntityManager manager;

    @Autowired
    private AirportRepository airportRepository;

    @Test
    public void shouldReturnAirportForFindByName(){
        Airport airport = new Airport();
        manager.persist(airport);
        manager.flush();
        Airport desired = airportRepository.findByAirportName(airport.getAirportName());
        assertEquals(airport,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidName(){
        Airport airport = airportRepository.findByAirportName("not-existing");
        assertNull(airport);
    }
}
