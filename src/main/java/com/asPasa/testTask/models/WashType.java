package com.asPasa.testTask.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor

public class WashType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long durationMinutes;
    public WashType(String name, Long durationMinutes){
        this.name=name;
        this.durationMinutes=durationMinutes;
    }
}
