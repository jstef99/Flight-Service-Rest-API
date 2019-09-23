package com.jstef.flight_service.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departurePlace;
    private String destination;
    private int duration;
    private String departureTime;
    private String arrivalTime;
    private int maxPassengers;
    private int currPassengers;
    @OneToMany(mappedBy = "flight")
    private List<Registration> registrations;

    public Flight(){
        currPassengers=0;
    }
}
