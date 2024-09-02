package com.asPasa.testTask.utils;

import com.asPasa.testTask.models.ReservationDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;


@Component
public class Scheduler {
    @Value("${schedule.startHour}")
    private int startHour;
    @Value("${schedule.endHour}")
    private int endHour;
    @Value("${schedule.minimalTimeMins}")
    private Long minimalTimeMins;


    private final Calendar start;
    private final Calendar end;

    public Scheduler() {
        start = new GregorianCalendar();
        start.set(Calendar.HOUR_OF_DAY, startHour);
        end = new GregorianCalendar();
        end.set(Calendar.HOUR_OF_DAY, endHour);
    }

    public List<Date> getAvailableForDate(Date date, List<ReservationDetails> existingReservations, Duration requiredTime) {
        List<Date> available = new ArrayList<>();
        Date endTime = getEndTimeForDate(date);
        Date current = getStartTimeForDate(date);
        for (ReservationDetails bookingDate : existingReservations) {
            if (current.getTime() + requiredTime.toMillis() < bookingDate.getStartDate().getTime()) {
                available.addAll(availableSlots(current, bookingDate.getStartDate(), requiredTime));
            }
            Duration duration = Duration.ofMinutes(bookingDate.getWashDurationMinutes());
            current.setTime(bookingDate.getStartDate().getTime() + duration.toMillis());
        }
        if (current.getTime() + requiredTime.toMillis() < endTime.getTime()) {
            available.addAll(availableSlots(current, endTime, requiredTime));
        }
        return available;

    }

    public Date getStartTimeForDate(Date date) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        startCalendar.set(Calendar.HOUR_OF_DAY, startHour);

        return startCalendar.getTime();
    }

    public Date getEndTimeForDate(Date date) {
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(date);
        endCalendar.set(Calendar.HOUR_OF_DAY, endHour);

        return endCalendar.getTime();
    }

    private List<Date> availableSlots(Date lastEndTime, Date nextStartTime, Duration requiredTime) {
        List<Date> available = new ArrayList<>();
        Date current = new Date(lastEndTime.getTime());
        while (current.getTime() + requiredTime.toMillis() <= nextStartTime.getTime()) {
            available.add(new Date(current.getTime()));
            current.setTime(current.getTime() + Duration.ofMinutes(minimalTimeMins).toMillis());
        }
        return available;
    }

    public boolean isSlotFree(Date requested, Duration estTime, List<ReservationDetails> existingReservations) {
        boolean isFree = true;

        Date endTime = getEndTimeForDate(requested);
        Date startTime = getStartTimeForDate(requested);
        if (requested.before(startTime) || requested.equals(startTime)) {
            isFree = false;
        }
        Date requestedEnd = new Date(requested.getTime() + estTime.toMillis());
        if (endTime.before(requestedEnd) || endTime.equals(requestedEnd)) {
            isFree = false;
        }

        for (ReservationDetails current : existingReservations) {
            if (requested.equals(current.getStartDate())) {
                isFree = false;
            }
            if (requestedEnd.equals(current.endDate())) {
                isFree = false;
            }

            if (requested.before(current.getStartDate()) &&
                    current.endDate().before(requestedEnd)
            ) {
                isFree = false;
            }
            if (current.getStartDate().before(requested) && requested.before(current.endDate())) {
                isFree = false;
            }
            if (requested.before(current.getStartDate()) && current.endDate().before(requestedEnd)) {
                isFree = false;
            }
            if (current.getStartDate().before(requested) && requestedEnd.before(current.endDate())) {
                isFree = false;
            }
        }
        return isFree;
    }

}
