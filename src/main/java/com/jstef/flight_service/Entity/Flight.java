package com.jstef.flight_service.Entity;

import lombok.Data;
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
    private int id;
    @ManyToOne
    @JoinColumn(name="departures")
    private Airport departurePlace;
    @ManyToOne
    @JoinColumn(name="destinations")
    private Airport destination;
    private int duration;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date departureTime;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date arrivalTime;
    private int maxPassengers;
    private int currPassengers;
    @OneToMany(mappedBy = "flight")
    private List<Registration> registrations;

    public Flight(){
        currPassengers=0;
    }
    public Flight(Airport departurePlace, Airport destination, int duration, Date departureTime, Date arrivalTime,int maxPassengers) throws ParseException {
        currPassengers=0;
        this.departurePlace=departurePlace;
        this.destination=destination;
        this.departureTime=departureTime;
        this.arrivalTime=arrivalTime;
        this.maxPassengers=maxPassengers;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date firstDate = sdf.parse(arrivalTime.toString());
        Date secondDate = sdf.parse(departureTime.toString());
        this.duration = (int)Math.abs(secondDate.getTime() - firstDate.getTime());
    }
}
