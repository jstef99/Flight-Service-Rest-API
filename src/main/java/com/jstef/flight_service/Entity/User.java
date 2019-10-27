package com.jstef.flight_service.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public void copyProperties(User user){
        this.id=user.getId();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.login=user.getLogin();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.reservations=user.getReservations();
        this.apiKey=user.getApiKey();
    }
}
