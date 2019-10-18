package com.jstef.flight_service.Helper;

import lombok.Data;

@Data
public class TicketTransaction {
    private int flightId;
    private int adults;
    private int children;
    private float normalTicketPrice;
    private float discountTicketPrice;

    public TicketTransaction(int flightId,float normalTicketPrice, float discountTicketPrice){
        this.flightId=flightId;
        this.normalTicketPrice=normalTicketPrice;
        this.discountTicketPrice=discountTicketPrice;
    }

    public float computeTotal() {
        return adults*normalTicketPrice+children*discountTicketPrice;
    }


}
