package com.jstef.flight_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name="airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="airport_id")
    private int id;

    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return id == airport.id &&
                Objects.equals(country, airport.country) &&
                Objects.equals(airportName, airport.airportName) &&
                Objects.equals(departures, airport.departures) &&
                Objects.equals(arrivals, airport.arrivals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, airportName, departures, arrivals);
    }

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
