package com.asPasa.testTask.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
@AllArgsConstructor
public class ReservationDetails {
    private final String washType;
    private final User booker;
    private final Date startDate;
    private final Long washDurationMinutes;

    public Date endDate(){
        return new Date(startDate.getTime()+ Duration.ofMinutes(washDurationMinutes).toMillis());
    }

}
