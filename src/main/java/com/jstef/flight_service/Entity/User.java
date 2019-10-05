package com.jstef.flight_service.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;
    private String firstName;
    private String lastName;
    @Column(name="user_email")
    private String email;
    @Column(name="user_login")
    private String login;
    @Column(name="user_password")
    private String password;
    @OneToMany(mappedBy = "owner")
    private List<Registration> reservations;
    private String apiKey;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role>roles;
    public User(){

    }
}
