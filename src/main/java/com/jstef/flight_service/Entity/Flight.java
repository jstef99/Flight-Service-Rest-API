package com.jstef.flight_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name="flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flight_id")
    private int id;
    @ManyToOne
    @JoinColumn(name="departure_airport_id")
    private Airport departurePlace;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                duration == flight.duration &&
                maxPassengers == flight.maxPassengers &&
                currPassengers == flight.currPassengers &&
                Float.compare(flight.nPrice, nPrice) == 0 &&
                Float.compare(flight.dPrice, dPrice) == 0 &&
                Objects.equals(departurePlace, flight.departurePlace) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(arrivalTime, flight.arrivalTime) &&
                Objects.equals(registrations, flight.registrations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departurePlace, destination, duration, departureTime, arrivalTime, maxPassengers, currPassengers, registrations, nPrice, dPrice);
    }

    @ManyToOne
    @JoinColumn(name="destination_airport_id")
    private Airport destination;
    private int duration;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date departureTime;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date arrivalTime;
    private int maxPassengers;
    private int currPassengers;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "flight")
    private List<Registration> registrations;
    @Column(name="normal_ticket_price")
    private float nPrice;
    @Column(name="discounted_ticket_price")
    private float dPrice;

    public Flight(){
        currPassengers=0;
    }
    public Flight(int id){
        this.id = id;
    }
    public Flight(Airport departurePlace, Airport destination, DateTime departureTime, DateTime arrivalTime, int maxPassengers,
                  float nPrice, float dPrice) throws ParseException {
        currPassengers=0;
        this.departurePlace=departurePlace;
        this.destination=destination;
        this.departureTime=departureTime.toDate();
        this.arrivalTime=arrivalTime.toDate();
        this.maxPassengers=maxPassengers;
        Seconds diff = Seconds.secondsBetween(departureTime,arrivalTime);
        this.duration = diff.getSeconds()/60;
    }

    public Flight(int id, int maxPassengers, int currPassengers){
        this.id = id;
        this.maxPassengers = maxPassengers;
        this.currPassengers = currPassengers;
    }

    public Flight(DateTime departureTime){
        this.departureTime=departureTime.toDate();
    }

    public void copyProperties(Flight flight) {
        this.nPrice=flight.getNPrice();
        this.dPrice=flight.getDPrice();
        this.currPassengers=flight.getCurrPassengers();
        this.maxPassengers=flight.getMaxPassengers();
        this.departureTime=flight.getDepartureTime();
        this.arrivalTime=flight.getArrivalTime();
        this.departurePlace=flight.getDeparturePlace();
        this.destination=flight.getDestination();
    }
}
