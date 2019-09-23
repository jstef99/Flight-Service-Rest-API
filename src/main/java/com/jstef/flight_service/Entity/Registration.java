package com.jstef.flight_service.Entity;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="registrations")
public class Registration {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    private int adults;

    private int children;
    private String status;

    public Registration(){

    }
}