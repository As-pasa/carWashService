package com.asPasa.testTask.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String username;
    private String email;
    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CarReservation> reservations;

    public User(String name, String email) {
        username=name;
        this.email=email;
        reservations=new ArrayList<>();
    }

    public void setState(User other){
        this.username=other.username;
        this.email=other.username;
        this.setReservations(other.getReservations());
    }
}
