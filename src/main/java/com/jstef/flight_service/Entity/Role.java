package com.jstef.flight_service.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="roles")
public class Role {

    @Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="role_name")
    private String name;

    public Role() {
    }
    public Role(String name) {
        this.name=name;
    }
}