package com.jstef.flight_service.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Role() {
    }
    public Role(String name) {
        this.name=name;
    }
}