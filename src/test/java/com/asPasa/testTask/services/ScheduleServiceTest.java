package com.asPasa.testTask.services;

import com.asPasa.testTask.models.SlotTimeInterval;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@TestPropertySource(properties = {
        "schedule.startHour=8",
        "schedule.dayDurationHours=12",
        "schedule.minimalTimeMinutes=120"
})
public class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void emptyBookedTest(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024,8,3,8,0,0);

        List<Date> dateList =scheduleService.findFreeSlots(calendar.getTime(), Duration.ofMinutes(30),new ArrayList<>());

        Assertions.assertEquals(dateList.size(),6);
    }
    @Test
    public void oneBefore(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024,8,3,8,0,0);
        Date start =calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        SlotTimeInterval bigEndInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(10));
        List<Date> dateList = scheduleService.findFreeSlots(start,Duration.ofMinutes(120),List.of(bigEndInterval));
        System.out.println(dateList.stream().map(s->s.getHours()).toList());
        Assertions.assertEquals(dateList.size(),1);
    }


    @Test
    public void twoBefore(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024,8,3,8,0,0);
        Date start =calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,13);
        SlotTimeInterval bigEndInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(10));
        List<Date> dateList = scheduleService.findFreeSlots(start,Duration.ofMinutes(120),List.of(bigEndInterval));
        System.out.println(dateList.stream().map(s->s.getHours()).toList());
        Assertions.assertEquals(dateList.size(),2);
    }

    @Test
    public void oneBetween(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024,8,3,8,0,0);
        Date start =calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,8);
        SlotTimeInterval bigStartInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(2));
        calendar.set(Calendar.HOUR_OF_DAY,13);
        SlotTimeInterval bigEndInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(10));
        List<Date> dateList = scheduleService.findFreeSlots(start,Duration.ofMinutes(120),List.of(bigStartInterval, bigEndInterval));
        System.out.println(dateList.stream().map(s->s.getHours()).toList());
        Assertions.assertEquals(dateList.size(),1);
    }

    @Test
    public void twoInBetween(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024,8,3,8,0,0);
        Date start =calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,6);
        SlotTimeInterval bigStartInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(2));
        calendar.set(Calendar.HOUR_OF_DAY,13);
        SlotTimeInterval bigEndInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(10));
        List<Date> dateList = scheduleService.findFreeSlots(start,Duration.ofMinutes(120),List.of(bigStartInterval, bigEndInterval));
        System.out.println(dateList.stream().map(s->s.getHours()).toList());
        Assertions.assertEquals(dateList.size(),2);
    }


    @Test
    public void oneLast(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(2024,8,3,8,0,0);
        Date start =calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY,8);
        SlotTimeInterval bigStartInterval = new SlotTimeInterval(calendar.getTime(),Duration.ofHours(9));

        List<Date> dateList = scheduleService.findFreeSlots(start,Duration.ofMinutes(120),List.of(bigStartInterval));
        System.out.println(dateList.stream().map(s->s.getHours()).toList());
        Assertions.assertEquals(dateList.size(),1);
    }
}
