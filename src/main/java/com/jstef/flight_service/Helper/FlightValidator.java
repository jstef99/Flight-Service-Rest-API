package com.jstef.flight_service.Helper;

import com.jstef.flight_service.Entity.Flight;
import com.jstef.flight_service.Entity.User;
import com.jstef.flight_service.Helper.TicketTransaction;

public class FlightValidator {
    public boolean validate(Flight flight, TicketTransaction transaction){
        if(transaction.getAdults()<0||transaction.getChildren()<0) return false;
        return (flight.getMaxPassengers()-flight.getCurrPassengers())>=(transaction.getAdults()+transaction.getChildren());
    }
}
