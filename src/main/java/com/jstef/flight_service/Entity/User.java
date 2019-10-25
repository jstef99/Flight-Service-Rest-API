package com.jstef.flight_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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
    @JsonIgnore
    @Column(name="user_password")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Registration> reservations;
    @JsonIgnore
    private String apiKey;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role>roles;
    public User(){

    }
    public User(String firstName, String lastName, String login, String email, String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.login=login;
        this.email=email;
        this.password=password;
    }
}
