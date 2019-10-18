package com.jstef.flight_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name="registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    private String confirmToken;

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

    public void createConfirmToken() {
        confirmToken = UUID.randomUUID().toString();
    }
}
