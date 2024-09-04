package com.asPasa.testTask.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "wash_type")
public class WashType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long requiredMinutes;

    @OneToMany(mappedBy = "washType",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<BookingSlot> slotsOfType;
    public WashType(String name, Long requiredMinutes){
        this.name=name;
        this.requiredMinutes=requiredMinutes;
    }
}
