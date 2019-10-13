package com.jstef.flight_service.Entity;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="registrations")
public class Registration {
    @Id
    @Column(name="registration_id")
    private int id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;

    private int adults;

    private int children;
    @Column(name="registration_status")
    private String status;

    private float total;

    public Registration(){

    }

    public Registration(int adults, int children, float total, User owner, Flight flight){
        this.status="pending";
        this.adults=adults;
        this.children=children;
        this.total=total;
        this.owner=owner;
        this.flight=flight;
    }
}
