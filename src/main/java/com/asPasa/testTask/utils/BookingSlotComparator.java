package com.asPasa.testTask.utils;

import com.asPasa.testTask.models.BookingSlot;

import java.util.Comparator;

public class BookingSlotComparator implements Comparator<BookingSlot> {
    @Override
    public int compare(BookingSlot o1, BookingSlot o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}
