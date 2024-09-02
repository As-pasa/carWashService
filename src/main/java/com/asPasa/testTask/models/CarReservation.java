package com.asPasa.testTask.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="reservations")
public class CarReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date reservationTime;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="washType_id")
    private WashType washType;

    public CarReservation(Date reservationTime, User user, WashType washType){
        this.user=user;
        this.reservationTime=reservationTime;
        this.washType=washType;
    }
}
