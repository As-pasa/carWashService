package com.asPasa.testTask.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootTest
public class SlotTimeIntervalTest {


    private final Calendar calendar = new GregorianCalendar();

    @BeforeEach
    void setup() {
        calendar.set(2024, 9, 3, 8, 0);

    }

    @Test
    void testNoIntersect() {
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(10));
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        SlotTimeInterval second = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(10));
        Assertions.assertFalse(first.intersect(second));
    }

    @Test
    void testFirstOverlaps() {
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(360));
        calendar.set(Calendar.MINUTE, 30);
        SlotTimeInterval sec = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(10));
        Assertions.assertTrue(first.intersect(sec));
    }

    @Test
    void testSecondOverlaps() {
        SlotTimeInterval sec = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(360));
        calendar.set(Calendar.MINUTE, 30);
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(10));
        Assertions.assertTrue(first.intersect(sec));
    }

    @Test
    void testFirstIsBeforeOverlaps() {
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(60));
        calendar.set(Calendar.MINUTE, 30);
        SlotTimeInterval sec = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(60));
        Assertions.assertTrue(first.intersect(sec));
    }

    @Test
    void testSecondBeforeOverlaps() {
        SlotTimeInterval sec = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(60));
        calendar.set(Calendar.MINUTE, 30);
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(60));
        Assertions.assertTrue(first.intersect(sec));
    }
    @Test
    void sameStart(){
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(30));
        SlotTimeInterval sec = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(60));
        Assertions.assertTrue(first.intersect(sec));
    }

    @Test
    void sameEnd(){
        SlotTimeInterval sec = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(60));
        calendar.set(Calendar.MINUTE, 30);
        SlotTimeInterval first = new SlotTimeInterval(calendar.getTime(), Duration.ofMinutes(30));
        Assertions.assertTrue(first.intersect(sec));

    }
}
