package com.jstef.flight_service.Entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "flight")
    private List<Registration> registrations;

    public Flight(){
        currPassengers=0;
    }
    public Flight(Airport departurePlace, Airport destination, DateTime departureTime, DateTime arrivalTime, int maxPassengers) throws ParseException {
        currPassengers=0;
        this.departurePlace=departurePlace;
        this.destination=destination;
        this.departureTime=departureTime.toDate();
        this.arrivalTime=arrivalTime.toDate();
        this.maxPassengers=maxPassengers;
        Seconds diff = Seconds.secondsBetween(departureTime,arrivalTime);
        this.duration = diff.getSeconds()/60;
    }
}
