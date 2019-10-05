package com.jstef.flight_service.Entity;

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

    @OneToMany(mappedBy="departurePlace")
    private List<Flight> departures;

    @OneToMany(mappedBy="destination")
    private List<Flight> arrivals;

    public Airport(){

    }
    public Airport(String country, String airportName){
        this.airportName=airportName;
        this.country=country;
    }
}
