package com.asPasa.testTask.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservations")
public class BookingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "wash_type_id")
    private WashType washType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    private Date startTime;

    public SlotTimeInterval getInterval() {
        return new SlotTimeInterval(startTime, Duration.ofMinutes(washType.getRequiredMinutes()));
    }
    public BookingSlot(User owner, WashType washType, Date startTime){
        this.owner=owner;
        this.startTime=startTime;
        this.washType=washType;
    };

}
