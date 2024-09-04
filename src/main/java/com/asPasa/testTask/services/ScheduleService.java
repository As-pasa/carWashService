package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.BookingSlot;
import com.asPasa.testTask.models.SlotTimeInterval;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final BookingSlotsService bookingSlotsService;
    @Value("${schedule.startHour}")
    private int startHour;

    @Value("${schedule.dayDurationHours}")
    private Long dayDurationHours;

    @Value("${schedule.minimalTimeMinutes}")
    private Long minimalTimeMinutes;

    private Calendar setCalendarOnDayStart(Date day) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, startHour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }


    public List<Date> findFreeSlots(Date dateSearched, Duration estTime, List<SlotTimeInterval> existingIntervals) {
        Calendar calendar = setCalendarOnDayStart(dateSearched);
        SlotTimeInterval allDayInterval = new SlotTimeInterval(calendar.getTime(), Duration.ofHours(dayDurationHours));

        SlotTimeInterval requested = new SlotTimeInterval(calendar.getTime(), estTime);
        List<Date> slots = new ArrayList<>();
        Date dayEnd = new Date(requested.getStartTime().getTime() + Duration.ofHours(dayDurationHours).toMillis());
        if (existingIntervals.isEmpty()) {
            return populate(requested.getStartTime(), dayEnd, estTime);
        }


        for (SlotTimeInterval booked : existingIntervals) {
            if (!requested.intersect(booked)) {
                slots.addAll(populate(requested.getStartTime(), booked.getStartTime(), estTime));
            }
            requested.setStartTime(booked.end());
        }
        if (!requested.intersect(new SlotTimeInterval(dayEnd, Duration.ofMinutes(10)))) {
            slots.addAll(populate(requested.getStartTime(), dayEnd, estTime));
        }
        return slots;
    }

    public BookingSlot book(BookingSlot slot) {
        if (!isSlotFree(slot.getStartTime(), Duration.ofMinutes(slot.getWashType().getRequiredMinutes()))) {
            throw new ApplicationException("Slot already booked");
        }
        return bookingSlotsService.persistBooking(slot);
    }

    public Date findFirstFreeSlot(Duration estTime) {
        Date date = new Date();
        List<Date> dates = new ArrayList<>();
        Calendar startDay = setCalendarOnDayStart(date);
        while (dates.isEmpty()) {
            List<SlotTimeInterval> intervals = getIntervalsForDate(date);
            dates.addAll(findFreeSlots(date, estTime, intervals));
            startDay.set(Calendar.DATE, startDay.get(Calendar.DATE) + 1);
        }
        return dates.getFirst();
    }

    public boolean isSlotFree(Date slotStart, Duration slotRequestTime) {
        List<SlotTimeInterval> intervals = getIntervalsForDate(slotStart);
        SlotTimeInterval allDayInterval = getDayInterval(slotStart);
        SlotTimeInterval requestedInterval = new SlotTimeInterval(slotStart, slotRequestTime);
        if (!requestedInterval.intersect(allDayInterval)) {
            return false;
        }
        return intervals.stream().noneMatch(x -> x.intersect(requestedInterval));
    }

    public List<SlotTimeInterval> getIntervalsForDate(Date date) {
        Calendar startDay = setCalendarOnDayStart(date);
        SlotTimeInterval allDayInterval = getDayInterval(date);
        return bookingSlotsService.getIntervalsIn(allDayInterval);
    }

    public SlotTimeInterval getDayInterval(Date date) {
        Calendar startDay = setCalendarOnDayStart(date);
        return new SlotTimeInterval(startDay.getTime(), Duration.ofHours(dayDurationHours));
    }

    private List<Date> populate(Date prevEnd, Date nextStart, Duration estTime) {
        List<Date> slots = new ArrayList<>();
        Date cur = new Date(prevEnd.getTime());
        while (cur.getTime() + estTime.toMillis() < nextStart.getTime()) {
            slots.add(new Date(cur.getTime()));
            cur.setTime(cur.getTime() + Duration.ofMinutes(minimalTimeMinutes).toMillis());
        }
        return slots;
    }
}
