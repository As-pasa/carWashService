package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.CarReservation;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.utils.Scheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final Scheduler scheduler;
    private final ReservationDetailsService detailsService;
    private final ReservationService reservationService;

    public List<Date> getAvailableForDate(Date date, Duration reservationTime) {
        return scheduler.getAvailableForDate(date, detailsService.reservationsForDay(date), reservationTime);
    }

    public Boolean isTimeFree(Date start, Duration estTime) {
        return scheduler.isSlotFree(start, estTime, detailsService.reservationsForDay(start));
    }

    public Date getFirstAvailable(Duration reservationTime) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        List<Date> reservationOptions = new ArrayList<>();
        while (reservationOptions.isEmpty()) {
            reservationOptions.addAll(getAvailableForDate(calendar.getTime(), reservationTime));
            calendar.add(Calendar.DATE, 1);
        }
        return reservationOptions.getFirst();
    }

    public CarReservation bookSlot(User user, Date date, WashType washType){
        if(isTimeFree(date,Duration.ofMinutes(washType.getDurationMinutes()))){
            return reservationService.save(new CarReservation(date,user,washType));
        }
        throw new ApplicationException("Time suggested is already booked");
    }

}
