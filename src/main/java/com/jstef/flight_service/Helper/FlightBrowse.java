package com.jstef.flight_service.Helper;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FlightBrowse {
    private String departurePlace;
    private String destination;
    private String departureTime;

    public FlightBrowse(){

    }

    public FlightBrowse(String departureTime,String departurePlace, String destination){
        this.departurePlace=departurePlace;
        this.destination=destination;
        this.departureTime=departureTime;
    }
}
