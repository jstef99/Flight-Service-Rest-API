package com.jstef.flight_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="airport_id")
    private int id;

    private String country;

    private String airportName;

    @JsonIgnore
    @OneToMany(mappedBy="departurePlace")
    private List<Flight> departures;

    @JsonIgnore
    @OneToMany(mappedBy="destination")
    private List<Flight> arrivals;

    public Airport(){

    }
    public Airport(String country, String airportName){
        this.airportName=airportName;
        this.country=country;
    }

    public void copyProperties(Airport airport) {
        this.id=airport.getId();
        this.country=airport.getCountry();
        this.airportName=airport.getAirportName();
        this.departures=airport.getDepartures();
        this.arrivals=airport.getArrivals();
    }
}
