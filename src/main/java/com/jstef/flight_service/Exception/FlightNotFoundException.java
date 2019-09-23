package com.jstef.flight_service.Exception;

import java.util.function.Supplier;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(){
        super("");
    }
    public FlightNotFoundException(String message){
        super(message);
    }
}
